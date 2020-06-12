package game.states;

import game.singletons.Data;
import game.Game;
import game.IState;
import org.junit.jupiter.api.Test;
import resources.IResource;
import resources.types.Artefact;
import resources.types.BlackResource;
import ship.MiningShip;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiplyTest {

    @Test
    void finishTest1() {

        Data.getInstance().setShip(new MiningShip());
        Data.getInstance().setResource(new BlackResource());

        Game.getInstance().setState(new Multiply());
        Game.getInstance().finish();

        IState state = Game.getInstance().getState();
        assertEquals(state.getClass(), WaitInSpace.class);
    }

    @Test
    void finishTest2() {

        Data.getInstance().setShip(new MiningShip());

        Data.getInstance().setResource(new Artefact());

        Game.getInstance().setState(new Multiply());
        Game.getInstance().finish();

        IState state = Game.getInstance().getState();
        assertEquals(state.getClass(), WaitInSpace.class);
    }

    @Test
    void finishTest3() {

        Data.getInstance().setShip(new MiningShip());
        Data.getInstance().setResource(new Artefact());

        IResource[] artifact = new IResource[4];
        Arrays.fill(artifact, new Artefact());
        Data.getInstance().getShip().getCargo().loadResources(artifact);

        Game.getInstance().setState(new Multiply());
        Game.getInstance().finish();

        IState state = Game.getInstance().getState();
        assertEquals(state.getClass(), Win.class);
    }
}


