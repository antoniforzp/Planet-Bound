package space.spaceObject;

import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import game.singletons.Data;
import org.junit.jupiter.api.Test;
import ship.MiningShip;
import space.planet.Planet;
import space.planet.planets.BlackPlanet;

import static org.junit.jupiter.api.Assertions.*;

class SpaceObjectTest {

    @Test
    void wormholeConsumeShipTest() throws OutOfFuelException, CaptainDeletedException {

        Data.getInstance().setShip(new MiningShip());
        Wormhole wormhole = new Wormhole();

        int fuel = Data.getInstance().getShip().getFuel();
        int cells = Data.getInstance().getShip().getShield().getCells();

        //test fully equipped ship
        wormhole.consumeShip();

        int fuelNew = Data.getInstance().getShip().getFuel();
        int cellsNew = Data.getInstance().getShip().getShield().getCells();

        assertEquals(fuel - 3, fuelNew);
        assertEquals(cells - 2, cellsNew);
    }

    @Test
    void wormholeConsumeShipTest1() throws OutOfFuelException, CaptainDeletedException {

        Data.getInstance().setShip(new MiningShip());
        Wormhole wormhole = new Wormhole();

        int fuel = Data.getInstance().getShip().getFuel();
        int crew = Data.getInstance().getShip().getCrew().size();

        Data.getInstance().getShip().getShield().takeDamage(100);
        //test ship with no shields
        wormhole.consumeShip();

        int fuelNew = Data.getInstance().getShip().getFuel();
        int crewNew = Data.getInstance().getShip().getCrew().size();

        assertEquals(fuel - 3, fuelNew);
        assertEquals(crew - 1, crewNew);
    }

    @Test
    void wormholeConsumeShipTest2() throws OutOfFuelException, CaptainDeletedException {

        Data.getInstance().setShip(new MiningShip());
        Wormhole wormhole = new Wormhole();

        int fuel = Data.getInstance().getShip().getFuel();
        int cells = Data.getInstance().getShip().getShield().getCells();

        Data.getInstance().getShip().looseCrewMember();
        Data.getInstance().getShip().looseCrewMember();
        Data.getInstance().getShip().looseCrewMember(); //shield officer lost

        //test ship with no shields
        wormhole.consumeShip();

        int fuelNew = Data.getInstance().getShip().getFuel();
        int cellsNew = Data.getInstance().getShip().getShield().getCells();

        assertEquals(fuel - 4, fuelNew);
        assertEquals(cells - 2, cellsNew);
    }

    @Test
    void eventConsumeShipTest() throws OutOfFuelException {
        Data.getInstance().setShip(new MiningShip());
        Event event = new Event();

        int fuel = Data.getInstance().getShip().getFuel();
        event.consumeShip();
        int fuelNew = Data.getInstance().getShip().getFuel();

        assertEquals(fuel - 1, fuelNew);
    }

    @Test
    void routeOutConsumeShipTest() throws OutOfFuelException {
        Data.getInstance().setShip(new MiningShip());
        RouteOut routeOut = new RouteOut();

        int fuel = Data.getInstance().getShip().getFuel();
        routeOut.consumeShip();
        int fuelNew = Data.getInstance().getShip().getFuel();

        assertEquals(fuel - 1, fuelNew);
    }

    @Test
    void planetConsumeShipTest() throws OutOfFuelException {
        Data.getInstance().setShip(new MiningShip());
        Planet planet = new BlackPlanet();

        int fuel = Data.getInstance().getShip().getFuel();
        planet.consumeShip();
        int fuelNew = Data.getInstance().getShip().getFuel();

        assertEquals(fuel - 1, fuelNew);
    }
}