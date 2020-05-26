package game.states;

import exceptions.UnavailableException;
import exceptions.WrongArgumentException;
import game.Game;
import logic.ConvertLogic;
import logic.singleton.LogicConfig;

public class Convert implements IState {

    private static IState instance;
    private final ConvertLogic logic;

    public Convert() {
        logic = new ConvertLogic();
    }

    public static IState getInstance() {
        if (instance == null)
            instance = new Convert();
        return instance;
    }

    @Override
    public boolean chooseShip(int choice) throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean startConvert() throws UnavailableException {
        throw new UnavailableException();
    }

    //USABILITY
    @Override
    public boolean convert(int choice) throws WrongArgumentException {

        boolean check;
        switch (choice) {
            case 1: {
                check = logic.chargeShieldCell();
            }
            break;
            case 2: {
                check = logic.loadOneAmmunition();
            }
            break;
            case 3: {
                check = logic.chargeOneFuel();
            }
            break;
            default: {
                throw new WrongArgumentException();
            }
        }

        //when there is no option to convert fuel
        if (choice == 1 && !check && LogicConfig.getInstance().isRunOutOfFuel()) {
            Game.setState(GameOver.getInstance());
            return false;
        } else {
            //continue upgrading
            LogicConfig.getInstance().setRunOutOfFuel(false);
            Game.setState(Convert.getInstance());
            return check;
        }
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

        boolean check = LogicConfig.getInstance().isRunOutOfFuel();

        if (check) {
            Game.setState(Convert.getInstance());
        } else {
            Game.setState(WaitInSpace.getInstance());
        }
        return !check;
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
