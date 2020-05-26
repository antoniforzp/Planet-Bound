package game.states;

import exceptions.UnavailableException;
import game.Game;
import logic.singleton.LogicConfig;
import org.junit.jupiter.api.Test;
import ship.MiningShip;
import walker.alien.aliens.testing.BadassAlien;
import walker.alien.aliens.testing.WeakAlien;

import static org.junit.jupiter.api.Assertions.*;

class FightTest {

    @Test
    void fightTest1() throws UnavailableException {

        LogicConfig.getInstance().setShip(new MiningShip());
        LogicConfig.getInstance().setAlien(new BadassAlien());
        Game.setState(Fight.getInstance());

        assertFalse(Game.fight());

        IState state = Game.getState();
        assertEquals(state, WaitInSpace.getInstance());
    }

    @Test
    void fightTest2() throws UnavailableException {

        LogicConfig.getInstance().setShip(new MiningShip());
        LogicConfig.getInstance().setAlien(new WeakAlien());
        Game.setState(Fight.getInstance());

        assertTrue(Game.fight());

        IState state = Game.getState();
        assertEquals(state, ExplorePlanet.getInstance());
    }
}