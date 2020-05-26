package game.states;

import exceptions.UnavailableException;
import exceptions.WrongArgumentException;
import game.Game;
import logic.UpgradeLogic;

public class Upgrade implements IState {

    protected static IState instance;
    private final UpgradeLogic logic;

    public static IState getInstance() {
        if (instance == null)
            instance = new Upgrade();
        return instance;
    }

    public Upgrade() {
        this.logic = new UpgradeLogic();
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

    //USABILITY
    @Override
    public boolean upgrade(int choice) throws WrongArgumentException {

        boolean check;
        switch (choice) {
            case 1: {
                check = logic.refillMiningDrone();
            }
            break;
            case 2: {
                check = logic.upgradeCargoHold();
            }
            break;
            case 3: {
                check = logic.hireNewMember();
            }
            break;
            case 4: {
                check = logic.upgradeWeaponSystem();
            }
            break;
            case 5: {
                check = logic.buyNewMiningDrone();
            }
            break;
            default: {
                throw new WrongArgumentException();
            }
        }

        //continue upgrading
        Game.setState(Upgrade.getInstance());
        return check;
    }

    //USABILITY
    @Override
    public boolean finish() {

        //exit the state -> come back to WaitInSpace state
        Game.setState(WaitInSpace.getInstance());
        return true;
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
