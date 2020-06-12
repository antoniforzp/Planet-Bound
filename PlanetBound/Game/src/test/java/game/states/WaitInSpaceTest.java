package game.states;

import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import game.singletons.Data;
import game.Game;
import game.IState;
import org.junit.jupiter.api.Test;
import ship.MiningShip;
import space.SpaceSector;
import space.planet.Planet;
import space.planet.planets.BlackPlanet;
import space.spaceObject.Wormhole;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WaitInSpaceTest {

    @Test
    void startConvert1() throws CaptainDeletedException {
        Data.getInstance().setShip(new MiningShip());
        Data.getInstance().getShip().looseCrewMember();

        Game.getInstance().setState(new WaitInSpace());

        Game.getInstance().startConvert();
        IState state = Game.getInstance().getState();

        assertEquals(state.getClass(), WaitInSpace.class);
    }

    @Test
    void startConvert2() {
        Data.getInstance().setShip(new MiningShip());

        Game.getInstance().setState(new WaitInSpace());

        Game.getInstance().startConvert();
        IState state = Game.getInstance().getState();

        assertEquals(state.getClass(), Convert.class);
    }

    @Test
    void startUpgrade1() {
        Data.getInstance().setShip(new MiningShip());
        Game.getInstance().setState(new WaitInSpace());

        Data.getInstance().setPosition(0);
        Game.getInstance().startUpgrade();

        IState state = Game.getInstance().getState();
        assertEquals(state.getClass(), WaitInSpace.class);
    }

    @Test
    void startUpgrade2() {
        Data.getInstance().setShip(new MiningShip());
        Game.getInstance().setState(new WaitInSpace());

        Planet planet = new BlackPlanet();
        planet.setStation();

        Data.getInstance().setPlanet(planet);
        Data.getInstance().setPosition(2);
        Game.getInstance().startUpgrade();

        IState state = Game.getInstance().getState();
        assertEquals(state.getClass(), Upgrade.class);
    }

    @Test
    void travelToEvent1() {
        Data.getInstance().setShip(new MiningShip());
        Game.getInstance().setState(new WaitInSpace());

        Data.getInstance().setSpaceSector(new SpaceSector());
        Data.getInstance().setPosition(1);

        Game.getInstance().travel();

        IState state = Game.getInstance().getState();
        assertEquals(state.getClass(), WaitInSpace.class);
    }

    @Test
    void travelToEvent2() {
        Data.getInstance().setShip(new MiningShip());
        Game.getInstance().setState(new WaitInSpace());

        Data.getInstance().setSpaceSector(new SpaceSector());
        Data.getInstance().setPosition(0);

        Game.getInstance().travel();

        IState state = Game.getInstance().getState();
        assertEquals(state.getClass(), Event.class);
    }

    @Test
    void travelToGameOver() throws CaptainDeletedException {
        Data.getInstance().setShip(new MiningShip());
        Game.getInstance().setState(new WaitInSpace());

        Data.getInstance().setSpaceSector(new SpaceSector(true));
        Data.getInstance().setPosition(2);

        Data.getInstance().getShip().getShield().takeDamage(100);

        Data.getInstance().getShip().looseCrewMember();
        Data.getInstance().getShip().looseCrewMember();
        Data.getInstance().getShip().looseCrewMember();
        Data.getInstance().getShip().looseCrewMember();
        Data.getInstance().getShip().looseCrewMember();

        Game.getInstance().travel();
        assertEquals(Data.getInstance().getSpaceSector().getObjects()[3].getClass(), Wormhole.class);

        IState state = Game.getInstance().getState();
        assertEquals(state.getClass(), GameOver.class);
    }

    @Test
    void dropOnSurface1() {
        Data.getInstance().setShip(new MiningShip());
        Game.getInstance().setState(new WaitInSpace());

        Data.getInstance().setPlanet(new BlackPlanet());
        Data.getInstance().getPlanet().getRandomResource();
        Data.getInstance().getPlanet().getRandomResource();

        Game.getInstance().dropOnSurface();

        IState state = Game.getInstance().getState();
        assertEquals(state.getClass(), WaitInSpace.class);
    }

    @Test
    void dropOnSurface2() {
        Data.getInstance().setShip(new MiningShip());
        Data.getInstance().setPosition(2);
        Data.getInstance().setSpaceSector(new SpaceSector());

        Game.getInstance().setState(new WaitInSpace());

        Game.getInstance().dropOnSurface();

        IState state = Game.getInstance().getState();
        assertEquals(state.getClass(), ExplorePlanet.class);
    }

    @Test
    void dropOnSurface3() throws CaptainDeletedException {
        Data.getInstance().setShip(new MiningShip());
        Data.getInstance().setPosition(2);
        Data.getInstance().setSpaceSector(new SpaceSector());

        Game.getInstance().setState(new WaitInSpace());

        Data.getInstance().getShip().looseCrewMember();
        Data.getInstance().getShip().looseCrewMember();
        Data.getInstance().getShip().looseCrewMember();
        Data.getInstance().getShip().looseCrewMember();
        Data.getInstance().getShip().looseCrewMember();

        Game.getInstance().dropOnSurface();

        IState state = Game.getInstance().getState();
        assertEquals(state.getClass(), WaitInSpace.class);
    }
}