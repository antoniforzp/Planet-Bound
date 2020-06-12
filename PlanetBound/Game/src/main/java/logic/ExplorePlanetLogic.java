package logic;

import binding.properties.BooleanProperty;
import game.singletons.Data;
import space.planet.Planet;
import walker.Coordinate;
import walker.alien.Alien;
import walker.alien.AlienFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ExplorePlanetLogic implements Serializable {

    private final ArrayList<Coordinate> coordinates;
    private boolean alienMovement;

    //CONSTRUCTOR
    public ExplorePlanetLogic() {
        this.coordinates = new ArrayList<>();
        this.alienMovement = true;
    }

    //INITIALIZE
    public void setUpDropOnPlanet() {

        //get random resource from planet
        Planet planet = Data.getInstance().getPlanet();
        Data.getInstance().setResource(planet.getRandomResource());

        //reset aliens kills
        Data.getInstance().setAliensKilled(0);

        Random random = new Random();
        Coordinate c;
        int counter = 0;

        while (coordinates.size() < 3) {
            c = new Coordinate(random.nextInt(6), random.nextInt(6));

            //place randomly the extraction point and place there mining drone
            if (notContains(c) && counter == 0) {
                Data.getInstance().extPointCoordinateProperty().setInitialXY(c.getX(), c.getY());
                Data.getInstance().getShip().getDrone().setPositionInitial(c.getX(), c.getY());
                coordinates.add(c);
                counter++;
            }

            //place randomly alien
            if (notContains(c) && counter == 1) {
                Data.getInstance().alienPosCoordinateProperty().setInitialXY(c.getX(), c.getY());
                Data.getInstance().getAlien().setPositionInitial(c.getX(), c.getY());
                coordinates.add(c);
                counter++;
            }

            //place randomly resource
            if (notContains(c) && counter == 2) {
                Data.getInstance().resCoordinateProperty().setInitialXY(c.getX(), c.getY());
                coordinates.add(c);
                counter++;
            }
        }
    }

    //SETTING COORDINATES
    public void setAlienMovement(boolean alienMovement) {
        this.alienMovement = alienMovement;
        if (alienMovement) {
            Data.getInstance().getAlien().setPositionInitial(-1, -1);
            Data.getInstance().alienPosCoordinateProperty().setInitialXY(-1, -1);
        }
    }

    public void setAlienCoordinate(Coordinate alienCoordinate) {
        Data.getInstance().getAlien().setPositionInitial(alienCoordinate.getX(), alienCoordinate.getY());
        Data.getInstance().alienPosCoordinateProperty().setInitialXY(alienCoordinate.getX(), alienCoordinate.getY());
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

        coordinates.remove(Data.getInstance().getAlien().getPosition());
        Data.getInstance().setAlien(AlienFactory.getRandomAlien());

        while (coordinates.size() < 3) {
            c = new Coordinate(random.nextInt(6), random.nextInt(6));

            //place randomly alien
            if (notContains(c)) {

                Alien alien = AlienFactory.getRandomAlien();
                if (alien != null) {
                    alien.setPositionInitial(c.getX(), c.getX());
                    Data.getInstance().alienPosCoordinateProperty().setX(c.getX());
                    Data.getInstance().alienPosCoordinateProperty().setY(c.getY());
                }
                coordinates.add(c);
                break;
            }
        }
    }

    //USABILITY
    public boolean move(int x, int y) {

        Data.getInstance().getAlien().setDestination(Data.getInstance().getShip().getDrone().getPosition());
        Data.getInstance().getShip().getDrone().moveOwn(x, y);

        Coordinate resourceCoordinate = Data.getInstance().getResourceCoordinate();
        Coordinate extPointCoordinate = Data.getInstance().getExtractionPoint();

        Data.getInstance().setAlienMetProperty(new BooleanProperty(false));
        Data.getInstance().setAlienMet(false);

        if (Data.getInstance().getShip().getDrone().getPosition().equals(resourceCoordinate)) {
            Data.getInstance().getShip().getDrone().setDestination(extPointCoordinate);

            Data.getInstance().setResourceTaken(true);

        } else if (Data.getInstance().isResourceTaken() && Data.getInstance().getShip().getDrone().getPosition().equals(extPointCoordinate)) {

            return true;
        } else
            //check if player doesnt hit alien on his own
            if (Data.getInstance().getShip().getDrone().getPosition().equals(Data.getInstance().getAlien().getPosition())) {

                Data.getInstance().setAlienMet(true);
                getNewRandomAlien();
            } else {

                if (alienMovement) {
                    Data.getInstance().getAlien().moveTowardsDestination();
                }

                //check if alien after move doesnt hit the drone
                if (Data.getInstance().getShip().getDrone().getPosition().equals(Data.getInstance().getAlien().getPosition())) {

                    Data.getInstance().setAlienMet(true);
                    getNewRandomAlien();
                }
            }
        return false;
    }
}