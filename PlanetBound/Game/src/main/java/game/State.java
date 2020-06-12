package game;

import exceptions.UnavailableException;
import exceptions.WrongArgumentException;

import java.io.Serializable;

public class State implements IState, Cloneable, Serializable {

    @Override
    public State chooseShip(int choice) throws UnavailableException, WrongArgumentException {
        throw new UnavailableException();
    }

    @Override
    public State startConvert() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public State convert(int choice) throws UnavailableException, WrongArgumentException {
        throw new UnavailableException();
    }

    @Override
    public State startUpgrade() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public State upgrade(int choice) throws UnavailableException, WrongArgumentException {
        throw new UnavailableException();
    }

    @Override
    public State finish() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public State dropOnSurface() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public State fight() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public State move(int x, int y) throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public State travel() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public State processEvent(int choice) throws UnavailableException, WrongArgumentException {
        throw new UnavailableException();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
