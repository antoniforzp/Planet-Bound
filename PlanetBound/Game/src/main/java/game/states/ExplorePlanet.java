package game.states;

import exceptions.UnavailableException;
import game.Game;
import logic.ExplorePlanetLogic;
import observer.IObserver;

import java.util.concurrent.Callable;

public class ExplorePlanet implements IState, IObserver {

    protected static IState instance;
    private final ExplorePlanetLogic logic;

    private boolean alienMet;
    private boolean extractionPoint;


    public ExplorePlanet() {
        this.logic = new ExplorePlanetLogic();
        this.logic.addObserver(this);

        this.alienMet = false;
        this.extractionPoint = false;
    }

    public static IState getInstance() {
        if (instance == null)
            instance = new ExplorePlanet();
        return instance;
    }

    public ExplorePlanetLogic getLogic() {
        return logic;
    }

    @Override
    public boolean chooseShip(int choice) throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean startConvert() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean convert(int choice) throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean startUpgrade() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean upgrade(int choice) throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean finish() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean dropOnSurface() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean fight() throws UnavailableException {
        throw new UnavailableException();
    }

    //USABILITY
    @Override
    public boolean move(int x, int y) {

        boolean check = logic.move(x, y);

        System.out.println("EXT:" + extractionPoint);
        System.out.println("ALIEN:" + alienMet);

        if (!extractionPoint) {
            if (!alienMet) {
                if (check) {
                    Game.setState(WaitInSpace.getInstance());
                } else {
                    Game.setState(ExplorePlanet.getInstance());
                }
            }
        }

        this.alienMet = false;
        this.extractionPoint = false;

        return check;
    }

    @Override
    public boolean travel() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean processEvent(int choice) throws UnavailableException {
        throw new UnavailableException();
    }


    @Override
    public void update(String property) {
        switch (property) {
            case "ResourceTaken":
                System.out.println("Resource taken");
                Game.setState(ExplorePlanet.getInstance());
                break;
            case "AlienMet":
                System.out.println("AlienMet");
                Game.setState(Fight.getInstance());
                alienMet = true;
                break;
            case "ExtractionPoint":
                System.out.println("ExtractionPoint");
                extractionPoint = true;
                Game.setState(Multiply.getInstance());
                break;
        }
    }

    @Override
    public void assign(String property, Callable<Void> update) {

    }
}
