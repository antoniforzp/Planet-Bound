package game;

import exceptions.*;

public interface IState {

    State chooseShip(int choice) throws UnavailableException, WrongArgumentException;

    State startConvert() throws UnavailableException;

    State convert(int choice) throws UnavailableException, WrongArgumentException;

    State startUpgrade() throws UnavailableException;

    State upgrade(int choice) throws UnavailableException, WrongArgumentException;

    State finish() throws UnavailableException;

    State dropOnSurface() throws UnavailableException;

    State fight() throws UnavailableException;

    State move(int x, int y) throws UnavailableException;

    State travel() throws UnavailableException;

    State processEvent(int choice) throws UnavailableException, WrongArgumentException;
}