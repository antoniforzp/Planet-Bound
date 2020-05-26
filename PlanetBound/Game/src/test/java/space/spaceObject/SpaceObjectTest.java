package space.spaceObject;

import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import logic.singleton.LogicConfig;
import org.junit.jupiter.api.Test;
import ship.MilitaryShip;
import ship.MiningShip;
import space.planet.Planet;
import space.planet.planets.BlackPlanet;

import static org.junit.jupiter.api.Assertions.*;

class SpaceObjectTest {

    @Test
    void wormholeConsumeShipTest() throws OutOfFuelException, CaptainDeletedException {

        LogicConfig.getInstance().setShip(new MiningShip());
        Wormhole wormhole = new Wormhole();

        int fuel = LogicConfig.getInstance().getShip().getFuel();
        int cells = LogicConfig.getInstance().getShip().getShield().getCells();

        //test fully equipped ship
        wormhole.consumeShip();

        int fuelNew = LogicConfig.getInstance().getShip().getFuel();
        int cellsNew = LogicConfig.getInstance().getShip().getShield().getCells();

        assertEquals(fuel - 3, fuelNew);
        assertEquals(cells - 2, cellsNew);
    }

    @Test
    void wormholeConsumeShipTest1() throws OutOfFuelException, CaptainDeletedException {

        LogicConfig.getInstance().setShip(new MiningShip());
        Wormhole wormhole = new Wormhole();

        int fuel = LogicConfig.getInstance().getShip().getFuel();
        int crew = LogicConfig.getInstance().getShip().getCrew().size();

        LogicConfig.getInstance().getShip().getShield().takeDamage(100);
        //test ship with no shields
        wormhole.consumeShip();

        int fuelNew = LogicConfig.getInstance().getShip().getFuel();
        int crewNew = LogicConfig.getInstance().getShip().getCrew().size();

        assertEquals(fuel - 3, fuelNew);
        assertEquals(crew - 1, crewNew);
    }

    @Test
    void wormholeConsumeShipTest2() throws OutOfFuelException, CaptainDeletedException {

        LogicConfig.getInstance().setShip(new MiningShip());
        Wormhole wormhole = new Wormhole();

        int fuel = LogicConfig.getInstance().getShip().getFuel();
        int cells = LogicConfig.getInstance().getShip().getShield().getCells();

        LogicConfig.getInstance().getShip().looseCrewMember();
        LogicConfig.getInstance().getShip().looseCrewMember();
        LogicConfig.getInstance().getShip().looseCrewMember(); //shield officer lost

        //test ship with no shields
        wormhole.consumeShip();

        int fuelNew = LogicConfig.getInstance().getShip().getFuel();
        int cellsNew = LogicConfig.getInstance().getShip().getShield().getCells();

        assertEquals(fuel - 4, fuelNew);
        assertEquals(cells - 2, cellsNew);
    }

    @Test
    void eventConsumeShipTest() throws OutOfFuelException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Event event = new Event();

        int fuel = LogicConfig.getInstance().getShip().getFuel();
        event.consumeShip();
        int fuelNew = LogicConfig.getInstance().getShip().getFuel();

        assertEquals(fuel - 1, fuelNew);
    }

    @Test
    void routeOutConsumeShipTest() throws OutOfFuelException {
        LogicConfig.getInstance().setShip(new MiningShip());
        RouteOut routeOut = new RouteOut();

        int fuel = LogicConfig.getInstance().getShip().getFuel();
        routeOut.consumeShip();
        int fuelNew = LogicConfig.getInstance().getShip().getFuel();

        assertEquals(fuel - 1, fuelNew);
    }

    @Test
    void planetConsumeShipTest() throws OutOfFuelException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Planet planet = new BlackPlanet();

        int fuel = LogicConfig.getInstance().getShip().getFuel();
        planet.consumeShip();
        int fuelNew = LogicConfig.getInstance().getShip().getFuel();

        assertEquals(fuel - 1, fuelNew);
    }
}