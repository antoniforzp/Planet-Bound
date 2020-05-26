package game.states;

import exceptions.UnavailableException;
import exceptions.WrongArgumentException;
import game.Game;
import logic.singleton.LogicConfig;
import ship.MilitaryShip;
import ship.MiningShip;

public class ChooseShip implements IState {

    protected static IState instance;

    public static IState getInstance() {
        if (instance == null)
            instance = new ChooseShip();
        return instance;
    }

    //USABILITY
    @Override
    public boolean chooseShip(int choice) throws WrongArgumentException {
        switch (choice) {
            case 1: {
                LogicConfig.getInstance().setShip(new MiningShip());
            }
            break;
            case 2: {
                LogicConfig.getInstance().setShip(new MilitaryShip());
            }
            break;
            default: {
                throw new WrongArgumentException();
            }
        }
        //--->wainInSpace state
        Game.setState(WaitInSpace.getInstance());
        return true;
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
