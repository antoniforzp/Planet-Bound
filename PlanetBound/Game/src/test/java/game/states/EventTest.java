package game.states;

import dice.Dice6;
import exceptions.*;
import game.Game;
import game.singletons.Data;
import game.IState;
import org.junit.jupiter.api.Test;
import ship.MiningShip;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void processEvent() {
        Data.getInstance().setShip(new MiningShip());
        Game.getInstance().setState(new Event());

        Game.getInstance().processEvent(Dice6.roll());

        IState state = Game.getInstance().getState();
        assertEquals(state.getClass(), WaitInSpace.class);
    }

    @Test
    void processEventGameOver() throws CaptainDeletedException {
        Data.getInstance().setShip(new MiningShip());
        Game.getInstance().setState(new Event());

        Data.getInstance().getShip().looseCrewMember();
        Data.getInstance().getShip().looseCrewMember();
        Data.getInstance().getShip().looseCrewMember();
        Data.getInstance().getShip().looseCrewMember();
        Data.getInstance().getShip().looseCrewMember();

        Game.getInstance().processEvent(1);

        IState state = Game.getInstance().getState();
        assertEquals(state.getClass(), GameOver.class);
    }
}