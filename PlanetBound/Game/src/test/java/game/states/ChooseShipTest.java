package game.states;

import game.singletons.Data;
import game.Game;
import game.IState;
import org.junit.jupiter.api.Test;
import ship.MiningShip;
import space.SpaceSector;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChooseShipTest {

    @Test
    void chooseShip() {
        Game.getInstance().setState(new ChooseShip());
        IState state = Game.getInstance().getState();

        assertEquals(state.getClass(), ChooseShip.class);
        Game.getInstance().chooseShip(1);
        IState newSate = Game.getInstance().getState();
        assertEquals(newSate.getClass(), WaitInSpace.class);
    }


    @Test
    void dropOnPlanetTest() {
        Game.getInstance().setState(new WaitInSpace());
        Data.getInstance().setShip(new MiningShip());
        Data.getInstance().setSpaceSector(new SpaceSector());

        Data.getInstance().setPosition(2);
        Game.getInstance().dropOnSurface();

        System.out.println(">>>>" + Data.getInstance().getResource());
        System.out.println(">>>>" + Data.getInstance().getShip().getDrone().getPosition());

        Game.getInstance().setState(new WaitInSpace());
        Game.getInstance().dropOnSurface();

        System.out.println(">>>>" + Data.getInstance().getResource());
        System.out.println(">>>>" + Data.getInstance().getShip().getDrone().getPosition());
    }
}