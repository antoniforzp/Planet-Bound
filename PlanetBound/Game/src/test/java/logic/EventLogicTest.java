package logic;

import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import exceptions.WrongArgumentException;
import game.singletons.Data;
import org.junit.jupiter.api.Test;
import resources.*;
import resources.types.BlackResource;
import resources.types.BlueResource;
import resources.types.GreenResource;
import resources.types.RedResource;
import ship.MilitaryShip;
import ship.Ship;
import ship.cargo.Cargo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class EventLogicTest {

    @Test
    void testCase0Default() {

        Data.getInstance().setShip(new MilitaryShip());
        EventLogic logic = new EventLogic();

        logic.setEventId(0);
        assertThrows(WrongArgumentException.class, logic::processEvent);
    }

    @Test
    void testCase1() throws CaptainDeletedException, OutOfFuelException, WrongArgumentException {

        Data.getInstance().setShip(new MilitaryShip());
        EventLogic logic = new EventLogic();
        Ship ship = Data.getInstance().getShip();

        //one crew member dies
        logic.setEventId(1);
        logic.processEvent();

        assertEquals(5, ship.getCrew().size());
    }

    @Test
    void testCase2() throws CaptainDeletedException, OutOfFuelException, WrongArgumentException {

        Data.getInstance().setShip(new MilitaryShip());
        EventLogic logic = new EventLogic();
        Cargo cargo = Data.getInstance().getShip().getCargo();

        //random resource is added
        logic.setEventId(2);
        logic.processEvent();

        //one of the cargoStorage is not empty any more
        assertTrue(!cargo.isBlacksEmpty() || !cargo.isBluesEmpty() || !cargo.isGreensEmpty() || !cargo.isRedsEmpty());
    }

    @Test
    void testCase3() throws CaptainDeletedException, OutOfFuelException, WrongArgumentException {

        Data.getInstance().setShip(new MilitaryShip());
        EventLogic logic = new EventLogic();
        Cargo cargo = Data.getInstance().getShip().getCargo();

        IResource[] resources = new IResource[20];
        Arrays.fill(resources, 0, 5, new RedResource());
        Arrays.fill(resources, 5, 10, new BlackResource());
        Arrays.fill(resources, 10, 15, new BlueResource());
        Arrays.fill(resources, 15, 20, new GreenResource());


        IResource[] blacks = new IResource[5];
        IResource[] blues = new IResource[5];
        IResource[] greens = new IResource[5];
        IResource[] reds = new IResource[5];

        Arrays.fill(blacks, new BlackResource());
        Arrays.fill(blues, new BlueResource());
        Arrays.fill(greens, new GreenResource());
        Arrays.fill(reds, new RedResource());

        cargo.loadResources(resources);

        //random resource is reduced
        logic.setEventId(3);
        logic.processEvent();

        //actual capacities are not equal with the previous ones
        assertFalse(cargo.getCapacity(blacks) == cargo.getCapacity(cargo.getBlacks()) &&
                cargo.getCapacity(blues) == cargo.getCapacity(cargo.getBlues()) &&
                cargo.getCapacity(greens) == cargo.getCapacity(cargo.getGreens()) &&
                cargo.getCapacity(reds) == cargo.getCapacity(cargo.getReds()));
    }

    @Test
    void testCase4() throws CaptainDeletedException, OutOfFuelException, WrongArgumentException {
        Data.getInstance().setShip(new MilitaryShip());
        EventLogic logic = new EventLogic();
        Ship ship = Data.getInstance().getShip();

        int fuel = ship.getFuel();

        logic.setEventId(4);
        logic.processEvent();

        //the fuel amount differs
        assertNotEquals(fuel, ship.getFuel());
    }

    @Test
    void testCase6() throws CaptainDeletedException, OutOfFuelException, WrongArgumentException {
        Data.getInstance().setShip(new MilitaryShip());
        EventLogic logic = new EventLogic();
        Ship ship = Data.getInstance().getShip();

        int crewSize = ship.getCrew().size();
        ship.looseCrewMember();

        logic.setEventId(6);
        logic.processEvent();

        //after the loss of one member, with new found sizes should match
        assertEquals(crewSize, ship.getCrew().size());
    }
}