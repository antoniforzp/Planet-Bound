package ship;

import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import logic.singleton.LogicConfig;
import org.junit.jupiter.api.Test;
import walker.miningDrone.MiningDrone;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    @Test
    void consumeFuel() throws OutOfFuelException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Ship ship = LogicConfig.getInstance().getShip();

        int newFuel;

        int fuel = ship.getFuel();
        assertTrue(ship.consumeFuel(5));
        newFuel = ship.getFuel();
        assertEquals(fuel - 5, newFuel);

        assertThrows(OutOfFuelException.class, () -> ship.consumeFuel(100));
    }

    @Test
    void chargeOneFuel() throws OutOfFuelException {

        LogicConfig.getInstance().setShip(new MiningShip());
        Ship ship = LogicConfig.getInstance().getShip();

        int newFuel;
        int fuel = ship.getFuel();
        ship.consumeFuel(1);
        ship.chargeOneFuel();

        newFuel = ship.getFuel();
        assertEquals(fuel, newFuel);
    }

    @Test
    void setNewDrone() {
        LogicConfig.getInstance().setShip(new MiningShip());
        Ship ship = LogicConfig.getInstance().getShip();

        MiningDrone drone = ship.getDrone();
        ship.setNewDrone();
        MiningDrone newDrone = ship.getDrone();

        assertNotSame(drone, newDrone);
    }

    @Test
    void looseCrewMember() throws CaptainDeletedException {

        LogicConfig.getInstance().setShip(new MiningShip());
        Ship ship = LogicConfig.getInstance().getShip();
        ship.looseCrewMember();
        ship.looseCrewMember();
        ship.looseCrewMember();
        ship.looseCrewMember();
        ship.looseCrewMember();
        assertThrows(CaptainDeletedException.class, ship::looseCrewMember);
    }

    @Test
    void looseCrewMember1() throws CaptainDeletedException {

        LogicConfig.getInstance().setShip(new MiningShip());
        Ship ship = LogicConfig.getInstance().getShip();

        int crew = ship.getCrew().size();
        ship.looseCrewMember();
        int newCrew = ship.getCrew().size();

        assertEquals(crew - 1, newCrew);
    }

    @Test
    void addNewCrewMember() throws CaptainDeletedException {
        LogicConfig.getInstance().setShip(new MiningShip());
        Ship ship = LogicConfig.getInstance().getShip();
        ship.looseCrewMember();

        int crew = ship.getCrew().size();
        ship.addNewCrewMember();
        int newCrew = ship.getCrew().size();

        assertEquals(crew + 1, newCrew);
    }


}