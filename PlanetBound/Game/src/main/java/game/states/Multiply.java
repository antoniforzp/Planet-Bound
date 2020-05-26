package game.states;

import dice.Dice6;
import exceptions.UnavailableException;
import game.Game;
import logic.MultiplyLogic;

public class Multiply implements IState {

    protected static IState instance;
    private final MultiplyLogic logic;

    public static IState getInstance() {
        if (instance == null)
            instance = new Multiply();
        return instance;
    }

    public Multiply() {
        this.logic = new MultiplyLogic();
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
    public boolean finish() {

        boolean check = logic.multiply(Dice6.roll());

        if (check) {
            Game.setState(Win.getInstance());
        } else {
            Game.setState(WaitInSpace.getInstance());
        }
        return check;
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
