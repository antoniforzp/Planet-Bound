package game.states;

import exceptions.UnavailableException;
import exceptions.WrongArgumentException;
import game.Game;
import logic.singleton.LogicConfig;
import org.junit.jupiter.api.Test;
import ship.MiningShip;

import static org.junit.jupiter.api.Assertions.*;

class UpgradeTest {

    @Test
    void upgrade() throws UnavailableException, WrongArgumentException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(Upgrade.getInstance());

        assertFalse(Game.upgrade(2));
        IState state = Game.getState();

        assertEquals(state, Upgrade.getInstance());
    }

    @Test
    void finish() throws UnavailableException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(Upgrade.getInstance());

        assertTrue(Game.finish());
        IState state = Game.getState();

        assertEquals(state, WaitInSpace.getInstance());
    }
}