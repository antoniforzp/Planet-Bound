package game.states;

import exceptions.UnavailableException;
import exceptions.WrongArgumentException;
import game.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChooseShipTest {

    @Test
    void chooseShip() throws UnavailableException, WrongArgumentException {
        Game.setState(ChooseShip.getInstance());
        IState state = Game.getState();
        assertEquals(state, ChooseShip.getInstance());
        Game.chooseShip(1);
        IState newSate = Game.getState();
        assertEquals(newSate, WaitInSpace.getInstance());
    }
}