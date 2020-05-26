package game.states;

import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import exceptions.UnavailableException;
import game.Game;
import logic.singleton.LogicConfig;
import org.junit.jupiter.api.Test;
import ship.MiningShip;
import space.SpaceSector;
import space.planet.Planet;
import space.planet.planets.BlackPlanet;
import space.spaceObject.Wormhole;

import static org.junit.jupiter.api.Assertions.*;

class WaitInSpaceTest {

    @Test
    void startConvert1() throws CaptainDeletedException, UnavailableException {
        LogicConfig.getInstance().setShip(new MiningShip());
        LogicConfig.getInstance().getShip().looseCrewMember();

        Game.setState(WaitInSpace.getInstance());

        assertFalse(Game.startConvert());
        IState state = Game.getState();

        assertEquals(state, WaitInSpace.getInstance());
    }

    @Test
    void startConvert2() throws UnavailableException {
        LogicConfig.getInstance().setShip(new MiningShip());

        Game.setState(WaitInSpace.getInstance());

        assertTrue(Game.startConvert());
        IState state = Game.getState();

        assertEquals(state, Convert.getInstance());
    }

    @Test
    void startUpgrade1() throws UnavailableException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(WaitInSpace.getInstance());

        LogicConfig.getInstance().setPosition(0);
        assertFalse(Game.startUpgrade());

        IState state = Game.getState();
        assertEquals(state, WaitInSpace.getInstance());
    }

    @Test
    void startUpgrade2() throws UnavailableException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(WaitInSpace.getInstance());

        Planet planet = new BlackPlanet();
        planet.setStation();

        LogicConfig.getInstance().setPlanet(planet);
        LogicConfig.getInstance().setPosition(2);
        assertTrue(Game.startUpgrade());

        IState state = Game.getState();
        assertEquals(state, Upgrade.getInstance());
    }

    @Test
    void travelToEvent1() throws UnavailableException, OutOfFuelException, CaptainDeletedException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(WaitInSpace.getInstance());

        LogicConfig.getInstance().setSpaceSector(new SpaceSector());
        LogicConfig.getInstance().setPosition(1);

        assertFalse(Game.travel());

        IState state = Game.getState();
        assertEquals(state, WaitInSpace.getInstance());
    }

    @Test
    void travelToEvent2() throws UnavailableException, OutOfFuelException, CaptainDeletedException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(WaitInSpace.getInstance());

        LogicConfig.getInstance().setSpaceSector(new SpaceSector());
        LogicConfig.getInstance().setPosition(0);

        assertTrue(Game.travel());

        IState state = Game.getState();
        assertEquals(state, Event.getInstance());
    }

    @Test
    void travelToGameOver() throws CaptainDeletedException, UnavailableException, OutOfFuelException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(WaitInSpace.getInstance());

        LogicConfig.getInstance().setSpaceSector(new SpaceSector(true));
        LogicConfig.getInstance().setPosition(2);

        LogicConfig.getInstance().getShip().getShield().takeDamage(100);

        LogicConfig.getInstance().getShip().looseCrewMember();
        LogicConfig.getInstance().getShip().looseCrewMember();
        LogicConfig.getInstance().getShip().looseCrewMember();
        LogicConfig.getInstance().getShip().looseCrewMember();
        LogicConfig.getInstance().getShip().looseCrewMember();

        Game.travel();
        assertEquals(LogicConfig.getInstance().getSpaceSector().getObjects()[3].getClass(), Wormhole.class);

        IState state = Game.getState();
        assertEquals(state, GameOver.getInstance());
    }

    @Test
    void travelToNoFuel() throws CaptainDeletedException, UnavailableException, OutOfFuelException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(WaitInSpace.getInstance());

        int fuel = LogicConfig.getInstance().getShip().getFuel();
        LogicConfig.getInstance().getShip().consumeFuel(fuel - 1);

        Game.travel();

        IState state = Game.getState();
        assertEquals(state, Convert.getInstance());
    }

    @Test
    void dropOnSurface1() throws UnavailableException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(WaitInSpace.getInstance());

        LogicConfig.getInstance().setPlanet(new BlackPlanet());
        LogicConfig.getInstance().getPlanet().getRandomResource();
        LogicConfig.getInstance().getPlanet().getRandomResource();

        assertFalse(Game.dropOnSurface());

        IState state = Game.getState();
        assertEquals(state, WaitInSpace.getInstance());
    }

    @Test
    void dropOnSurface2() throws UnavailableException {
        LogicConfig.getInstance().setShip(new MiningShip());
        LogicConfig.getInstance().setPosition(2);
        LogicConfig.getInstance().setSpaceSector(new SpaceSector());

        Game.setState(WaitInSpace.getInstance());

        assertTrue(Game.dropOnSurface());

        IState state = Game.getState();
        assertEquals(state, ExplorePlanet.getInstance());
    }

    @Test
    void dropOnSurface3() throws CaptainDeletedException {
        LogicConfig.getInstance().setShip(new MiningShip());
        LogicConfig.getInstance().setPosition(2);
        LogicConfig.getInstance().setSpaceSector(new SpaceSector());

        Game.setState(WaitInSpace.getInstance());

        LogicConfig.getInstance().getShip().looseCrewMember();
        LogicConfig.getInstance().getShip().looseCrewMember();
        LogicConfig.getInstance().getShip().looseCrewMember();
        LogicConfig.getInstance().getShip().looseCrewMember();
        LogicConfig.getInstance().getShip().looseCrewMember();

        assertFalse(Game.dropOnSurface());

        IState state = Game.getState();
        assertEquals(state, WaitInSpace.getInstance());
    }
}