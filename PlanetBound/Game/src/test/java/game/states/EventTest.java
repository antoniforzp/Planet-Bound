package game.states;

import dice.Dice6;
import exceptions.*;
import game.Game;
import logic.singleton.LogicConfig;
import org.junit.jupiter.api.Test;
import ship.MiningShip;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void processEvent() throws UnavailableException, WrongArgumentException, OutOfFuelException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(Event.getInstance());

        Game.processEvent(Dice6.roll());

        IState state = Game.getState();
        assertEquals(state, WaitInSpace.getInstance());
    }

    @Test
    void processEventGameOver() throws WrongArgumentException, CaptainDeletedException, UnavailableException, OutOfFuelException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(Event.getInstance());

        LogicConfig.getInstance().getShip().looseCrewMember();
        LogicConfig.getInstance().getShip().looseCrewMember();
        LogicConfig.getInstance().getShip().looseCrewMember();
        LogicConfig.getInstance().getShip().looseCrewMember();
        LogicConfig.getInstance().getShip().looseCrewMember();

        Game.processEvent(1);

        IState state = Game.getState();
        assertEquals(state, GameOver.getInstance());
    }

    @Test
    void processEventNoFuel1() throws WrongArgumentException, UnavailableException, OutOfFuelException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(Event.getInstance());

        int fuel = LogicConfig.getInstance().getShip().getFuel();
        LogicConfig.getInstance().getShip().consumeFuel(fuel - 1);
        Game.processEvent(4);

        IState state = Game.getState();
        assertEquals(state, Convert.getInstance());
    }
}