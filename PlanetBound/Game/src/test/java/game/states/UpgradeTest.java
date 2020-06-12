package game.states;

import game.singletons.Data;
import game.Game;
import game.IState;
import org.junit.jupiter.api.Test;
import ship.MiningShip;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpgradeTest {

    @Test
    void upgrade() {
        Data.getInstance().setShip(new MiningShip());
        Game.getInstance().setState(new Upgrade());

        Game.getInstance().upgrade(2);
        IState state = Game.getInstance().getState();

        assertEquals(state.getClass(), Upgrade.class);
    }

    @Test
    void finish() {
        Data.getInstance().setShip(new MiningShip());
        Game.getInstance().setState(new Upgrade());

        Game.getInstance().finish();
        IState state = Game.getInstance().getState();

        assertEquals(state.getClass(), WaitInSpace.class);
    }
}