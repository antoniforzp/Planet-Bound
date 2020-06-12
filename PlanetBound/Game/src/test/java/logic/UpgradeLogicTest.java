package logic;

import exceptions.CaptainDeletedException;
import game.singletons.Data;
import org.junit.jupiter.api.Test;
import resources.IResource;
import resources.types.BlackResource;
import resources.types.BlueResource;
import resources.types.GreenResource;
import resources.types.RedResource;
import ship.MilitaryShip;
import ship.MiningShip;
import ship.Ship;
import ship.cargo.Cargo;
import walker.miningDrone.MiningDrone;

import static org.junit.jupiter.api.Assertions.*;

class UpgradeLogicTest {

    @Test
    void refillMiningDroneTest() {
        Data.getInstance().setShip(new MiningShip());
        Cargo cargo = Data.getInstance().getShip().getCargo();
        MiningDrone drone = Data.getInstance().getShip().getDrone();

        UpgradeLogic logic = new UpgradeLogic();

        //try without required resources
        assertFalse(logic.refillMiningDrone());

        IResource[] required = new IResource[]{
                new RedResource(),
                new BlackResource(),
                new GreenResource(),
                new BlueResource(),
        };

        //attempt to refill fully charged mining drone
        cargo.loadResources(required);
        assertFalse(logic.refillMiningDrone());

        //attempt to refill damaged mining drone
        drone.takeDamage(4);
        cargo.loadResources(required);
    }

    @Test
    void upgradeCargoHoldTest() {

        Data.getInstance().setShip(new MiningShip());
        Cargo cargo = Data.getInstance().getShip().getCargo();

        UpgradeLogic logic = new UpgradeLogic();

        //try without required resources
        assertFalse(logic.upgradeCargoHold());

        IResource[] required = new IResource[]{
                new RedResource(), new RedResource(),
                new BlackResource(), new BlackResource(),
                new GreenResource(), new GreenResource(),
                new BlueResource(), new BlueResource()
        };

        //try with required resources to 1 level
        cargo.loadResources(required);
        assertTrue(logic.upgradeCargoHold());

        //try with required resources to 2 level
        cargo.loadResources(required);
        assertTrue(logic.upgradeCargoHold());
    }

    @Test
    void hireNewMemberTest() throws CaptainDeletedException {

        Data.getInstance().setShip(new MiningShip());
        Ship ship = Data.getInstance().getShip();
        Cargo cargo = ship.getCargo();

        UpgradeLogic logic = new UpgradeLogic();

        assertFalse(logic.upgradeCargoHold());

        IResource[] required = new IResource[]{
                new RedResource(),
                new BlackResource(),
                new GreenResource(),
                new BlueResource(),
        };

        //try to add crew member when full crew on board
        assertFalse(logic.hireNewMember());

        //loose one member and load required resources
        cargo.loadResources(required);
        ship.looseCrewMember();

        //try to hire with satisfying conditions
        assertTrue(logic.hireNewMember());
    }

    @Test
    void upgradeWeaponSystemTest() {

        Data.getInstance().setShip(new MiningShip());
        UpgradeLogic logic = new UpgradeLogic();

        //try upgrade on mining ship
        assertFalse(logic.upgradeWeaponSystem());
    }

    @Test
    void upgradeWeaponSystemTest1() {

        Data.getInstance().setShip(new MiningShip());
        Cargo cargo = Data.getInstance().getShip().getCargo();
        UpgradeLogic logic = new UpgradeLogic();

        IResource[] required = new IResource[]{
                new RedResource(), new RedResource(),
                new BlackResource(), new BlackResource(),
                new GreenResource(), new GreenResource(),
                new BlueResource(), new BlueResource()
        };

        //try to upgrade weapon in Mining drone
        assertFalse(logic.upgradeWeaponSystem());

        Data.getInstance().setShip(new MilitaryShip());
        cargo = Data.getInstance().getShip().getCargo();

        //try to upgrade without needed resources
        assertFalse(logic.upgradeWeaponSystem());

        //try to upgrade with everything needed
        cargo.loadResources(required);
        assertTrue(logic.upgradeWeaponSystem());
    }

    @Test
    void buyNewMiningDroneTest() {

        Data.getInstance().setShip(new MiningShip());
        Cargo cargo = Data.getInstance().getShip().getCargo();

        UpgradeLogic logic = new UpgradeLogic();

        IResource[] required = new IResource[]{
                new RedResource(), new RedResource(),
                new BlackResource(), new BlackResource(),
                new GreenResource(), new GreenResource(),
                new BlueResource(), new BlueResource()
        };

        //try to upgrade without needed resources
        assertFalse(logic.buyNewMiningDrone());

        cargo.loadResources(required);

        MiningDrone drone = Data.getInstance().getShip().getDrone();

        assertTrue(logic.buyNewMiningDrone());
        assertNotSame(drone, Data.getInstance().getShip().getDrone());
    }
}