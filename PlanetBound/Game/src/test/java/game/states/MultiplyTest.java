package game.states;

import exceptions.UnavailableException;
import game.Game;
import logic.singleton.LogicConfig;
import org.junit.jupiter.api.Test;
import resources.IResource;
import resources.types.Artefact;
import resources.types.BlackResource;
import ship.MiningShip;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MultiplyTest {

    @Test
    void finishTest1() throws UnavailableException {

        LogicConfig.getInstance().setShip(new MiningShip());
        LogicConfig.getInstance().setResource(new BlackResource());

        Game.setState(Multiply.getInstance());
        assertFalse(Game.finish());

        IState state = Game.getState();
        assertEquals(state, WaitInSpace.getInstance());
    }

    @Test
    void finishTest2() throws UnavailableException {

        LogicConfig.getInstance().setShip(new MiningShip());
        LogicConfig.getInstance().setResource(new Artefact());

        Game.setState(Multiply.getInstance());
        assertFalse(Game.finish());

        IState state = Game.getState();
        assertEquals(state, WaitInSpace.getInstance());
    }

    @Test
    void finishTest3() throws UnavailableException {

        LogicConfig.getInstance().setShip(new MiningShip());
        LogicConfig.getInstance().setResource(new Artefact());

        IResource[] artifact = new IResource[4];
        Arrays.fill(artifact, new Artefact());
        LogicConfig.getInstance().getShip().getCargo().loadResources(artifact);

        Game.setState(Multiply.getInstance());
        assertTrue(Game.finish());

        IState state = Game.getState();
        assertEquals(state, Win.getInstance());
    }
}


