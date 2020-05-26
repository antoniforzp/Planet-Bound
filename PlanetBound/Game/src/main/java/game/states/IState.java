package game.states;

import exceptions.*;

public interface IState {

    boolean chooseShip(int choice) throws UnavailableException, WrongArgumentException;

    boolean startConvert() throws UnavailableException;

    boolean convert(int choice) throws UnavailableException, WrongArgumentException;

    boolean startUpgrade() throws UnavailableException;

    boolean upgrade(int choice) throws UnavailableException, WrongArgumentException;

    boolean finish() throws UnavailableException;

    boolean dropOnSurface() throws UnavailableException;

    boolean fight() throws UnavailableException;

    boolean move(int x, int y) throws UnavailableException;

    boolean travel() throws UnavailableException;

    boolean processEvent(int choice) throws UnavailableException, WrongArgumentException;
}