package game.states;

import game.singletons.Data;
import game.Game;
import game.IState;
import org.junit.jupiter.api.Test;
import ship.MiningShip;
import walker.alien.aliens.testing.BadassAlien;
import walker.alien.aliens.testing.WeakAlien;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FightTest {

    @Test
    void fightTest1() {

        Data.getInstance().setShip(new MiningShip());
        Data.getInstance().setAlien(new BadassAlien());
        Game.getInstance().setState(new Fight());

        Game.getInstance().fight();

        IState state = Game.getInstance().getState();
        assertEquals(state.getClass(), WaitInSpace.class);
    }

    @Test
    void fightTest2() {

        Data.getInstance().setShip(new MiningShip());
        Data.getInstance().setAlien(new WeakAlien());
        Game.getInstance().setState(new Fight());

        Game.getInstance().fight();

        IState state = Game.getInstance().getState();
        assertEquals(state.getClass(), ExplorePlanet.class);
    }
}