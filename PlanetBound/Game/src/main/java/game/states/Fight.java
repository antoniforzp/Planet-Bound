package game.states;

import exceptions.UnavailableException;
import game.Game;
import logic.FightLogic;
import logic.singleton.LogicConfig;
import walker.alien.Alien;
import walker.miningDrone.MiningDrone;

public class Fight implements IState {

    protected static IState instance;
    private final FightLogic logic;

    public static IState getInstance() {
        if (instance == null)
            instance = new Fight();
        return instance;
    }

    public Fight() {
        this.logic = new FightLogic();
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

    //USABILITY
    @Override
    public boolean finish() {
        Game.setState(ExplorePlanet.getInstance());
        return true;
    }

    @Override
    public boolean dropOnSurface() throws UnavailableException {
        throw new UnavailableException();
    }


    @Override
    public boolean fight() {

        MiningDrone drone = LogicConfig.getInstance().getShip().getDrone();
        Alien alien = LogicConfig.getInstance().getAlien();

        boolean check = logic.fight(drone, alien);
        if (check) {
            Game.setState(ExplorePlanet.getInstance());
        } else {
            Game.setState(WaitInSpace.getInstance());
        }
        return check;
    }

    @Override
    public boolean move(int x, int y) throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean travel() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean processEvent(int choice) throws UnavailableException {
        throw new UnavailableException();
    }
}
