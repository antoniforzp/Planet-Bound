package logic;

import logic.singleton.LogicConfig;
import observer.IObservable;
import observer.IObserver;
import space.planet.IPlanet;
import space.planet.Planet;
import walker.Coordinate;
import walker.alien.Alien;
import walker.alien.AlienFactory;

import java.util.ArrayList;
import java.util.Random;

public class ExplorePlanetLogic implements IObservable {

    private final ArrayList<IObserver> observers = new ArrayList<>();

    private final ArrayList<Coordinate> coordinates;
    private boolean alienMovement;

    //CONSTRUCTOR

    public ExplorePlanetLogic() {
        this.coordinates = new ArrayList<>();
        this.alienMovement = true;
    }

    //INITIALIZE

    public void setRandomlyObjects() {

        Planet planet = LogicConfig.getInstance().getPlanet();
        LogicConfig.getInstance().setResource(planet.getRandomResource());
        System.out.println(planet.getResources().size());

        //reset aliens kill
        LogicConfig.getInstance().setAliensKilled(0);

        Random random = new Random();
        Coordinate c;
        int counter = 0;

        while (coordinates.size() < 3) {
            c = new Coordinate(random.nextInt(6), random.nextInt(6));

            //place randomly the extraction point and place there mining drone
            if (notContains(c) && counter == 0) {
                LogicConfig.getInstance().setExtractionPoint(c);
                LogicConfig.getInstance().getShip().getDrone().setPositionInitial(c.getX(), c.getY());
                coordinates.add(c);
                counter++;
            }

            //place randomly alien
            if (notContains(c) && counter == 1) {
                LogicConfig.getInstance().getAlien().setPositionInitial(c.getX(), c.getY());
                coordinates.add(c);
                counter++;
            }

            //place randomly resource
            if (notContains(c) && counter == 2) {
                System.out.println("RES COORD: " + c);
                LogicConfig.getInstance().setResourceCoordinate(c);
                coordinates.add(c);
                counter++;
            }
        }
    }

    //SETTING COORDINATES


    public void setAlienMovement(boolean alienMovement) {
        this.alienMovement = alienMovement;
        if (alienMovement) {
            LogicConfig.getInstance().getAlien().setPositionInitial(-1, -1);
        }
    }

    public void setAlienCoordinate(Coordinate alienCoordinate) {
        LogicConfig.getInstance().getAlien().setPositionInitial(alienCoordinate.getX(), alienCoordinate.getY());
    }

    private boolean notContains(Coordinate coordinate) {
        for (Coordinate c : coordinates) {
            if (c.getX() == coordinate.getX() && c.getY() == coordinate.getY()) {
                return false;
            }
        }
        return true;
    }

    public void getNewRandomAlien() {
        Random random = new Random();
        Coordinate c;

        coordinates.remove(LogicConfig.getInstance().getAlien().getPosition());
        LogicConfig.getInstance().setAlien(AlienFactory.getRandomAlien());

        while (coordinates.size() < 3) {
            c = new Coordinate(random.nextInt(6), random.nextInt(6));

            //place randomly alien
            if (notContains(c)) {

                Alien alien = AlienFactory.getRandomAlien();
                if (alien != null) {
                    alien.setPositionInitial(c.getX(), c.getX());
                }
                coordinates.add(c);
                break;
            }
        }
    }

    //USABILITY

    public boolean move(int x, int y) {


        LogicConfig.getInstance().getAlien().setDestination(LogicConfig.getInstance().getShip().getDrone().getPosition());
        LogicConfig.getInstance().getShip().getDrone().moveOwn(x, y);

        Coordinate resourceCoordinate = LogicConfig.getInstance().getResourceCoordinate();
        Coordinate extPointCoordinate = LogicConfig.getInstance().getExtractionPoint();

        if (LogicConfig.getInstance().getShip().getDrone().getPosition().equals(resourceCoordinate)) {
            LogicConfig.getInstance().getShip().getDrone().setDestination(extPointCoordinate);

            notifyChange("ResourceTaken");
            LogicConfig.getInstance().setResourceTaken(true);

        } else if (LogicConfig.getInstance().isResourceTaken() && LogicConfig.getInstance().getShip().getDrone().getPosition().equals(extPointCoordinate)) {

            notifyChange("ExtractionPoint");
            return true;
        } else
            //check if player doesnt hit alien on his own
            if (LogicConfig.getInstance().getShip().getDrone().getPosition().equals(LogicConfig.getInstance().getAlien().getPosition())) {

                notifyChange("AlienMet");
                getNewRandomAlien();
            } else {

                if (alienMovement) {
                    LogicConfig.getInstance().getAlien().moveTowardsDestination();
                }

                //check if alien after move doesnt hit the drone
                if (LogicConfig.getInstance().getShip().getDrone().getPosition().equals(LogicConfig.getInstance().getAlien().getPosition())) {

                    notifyChange("AlienMet");
                    getNewRandomAlien();
                }
            }
        return false;
    }

    @Override
    public void addObserver(IObserver observer) {
        deleteIfExists(observer.getClass());
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyChange(String property) {
        for (IObserver o : observers) {
            o.update(property);
        }
    }

    public void deleteIfExists(Class<?> classifier) {
        for (IObserver o : observers) {
            if (o.getClass() == classifier) {
                removeObserver(o);
            }
        }
    }
}