package game.states;

import exceptions.OutOfFuelException;
import game.singletons.Data;
import game.Game;
import game.IState;
import org.junit.jupiter.api.Test;
import ship.MiningShip;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConvertTest {

    @Test
    void convert() {
        Data.getInstance().setShip(new MiningShip());
        Game.getInstance().setState(new Convert());

        Game.getInstance().convert(1);
        IState state = Game.getInstance().getState();

        assertEquals(state.getClass(), Convert.class);
    }

    @Test
    void convertToGameOver() throws OutOfFuelException {
        Data.getInstance().setShip(new MiningShip());
        Game.getInstance().setState(new WaitInSpace());

        int fuel = Data.getInstance().getShip().getFuel();
        Data.getInstance().getShip().consumeFuel(fuel - 1);

        Game.getInstance().travel();
        Game.getInstance().convert(1);

        IState state = Game.getInstance().getState();

        assertEquals(state.getClass(), GameOver.class);
    }

    @Test
    void finish() {
        Data.getInstance().setShip(new MiningShip());
        Game.getInstance().setState(new Convert());

        Game.getInstance().finish();
        IState state = Game.getInstance().getState();

        assertEquals(state.getClass(), WaitInSpace.class);
    }
}