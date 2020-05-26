package game.states;

import exceptions.OutOfFuelException;
import exceptions.UnavailableException;
import exceptions.WrongArgumentException;
import game.Game;
import logic.singleton.LogicConfig;
import org.junit.jupiter.api.Test;
import ship.MiningShip;

import static org.junit.jupiter.api.Assertions.*;

class ConvertTest {

    @Test
    void convert() throws UnavailableException, WrongArgumentException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(Convert.getInstance());
        LogicConfig.getInstance().setRunOutOfFuel(false);

        assertFalse(Game.convert(1));
        IState state = Game.getState();

        assertEquals(state, Convert.getInstance());
    }

    @Test
    void convertToGameOver() throws UnavailableException, WrongArgumentException, OutOfFuelException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(WaitInSpace.getInstance());

        int fuel = LogicConfig.getInstance().getShip().getFuel();
        LogicConfig.getInstance().getShip().consumeFuel(fuel - 1);
        assertFalse(Game.travel());

        assertFalse(Game.convert(1));
        IState state = Game.getState();

        assertEquals(state, GameOver.getInstance());
    }

    @Test
    void finish() {
        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(Convert.getInstance());
        LogicConfig.getInstance().setRunOutOfFuel(false);

        assertTrue(Game.finish());
        IState state = Game.getState();

        assertEquals(state, WaitInSpace.getInstance());
    }

    @Test
    void finishWithNoFuel() {
        LogicConfig.getInstance().setShip(new MiningShip());
        LogicConfig.getInstance().setRunOutOfFuel(true);
        Game.setState(Convert.getInstance());

        assertFalse(Game.finish());

        IState state = Game.getState();
        assertEquals(state, Convert.getInstance());
    }
}