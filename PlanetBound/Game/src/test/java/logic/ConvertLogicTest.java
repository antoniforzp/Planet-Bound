package logic;

import exceptions.OutOfFuelException;
import game.singletons.Data;
import org.junit.jupiter.api.Test;
import resources.types.BlackResource;
import resources.types.BlueResource;
import resources.types.GreenResource;
import resources.IResource;
import ship.MilitaryShip;
import ship.Ship;

import static org.junit.jupiter.api.Assertions.*;

class ConvertLogicTest {

    @Test
    void chargeShieldCell() {

        Data.getInstance().setShip(new MilitaryShip());
        Ship ship = Data.getInstance().getShip();
        ConvertLogic logic = new ConvertLogic();

        //try to convert with full shield cells
        assertFalse(logic.chargeShieldCell());

        //try to convert without resources
        ship.getShield().takeDamage(1);
        assertFalse(logic.chargeShieldCell());

        //try to convert with resources and damaged shield
        IResource[] required = new IResource[]{
                new BlackResource(),
                new GreenResource(),
                new BlueResource(),
        };
        ship.getCargo().loadResources(required);
        assertTrue(logic.chargeShieldCell());
    }

    @Test
    void loadOneAmmunition() {
        Data.getInstance().setShip(new MilitaryShip());
        Ship ship = Data.getInstance().getShip();
        ConvertLogic logic = new ConvertLogic();

        //try to convert with full ammunition loaded
        assertFalse(logic.loadOneAmmunition());

        //try to convert without resources
        ship.getWeapon().fire(1);
        assertFalse(logic.loadOneAmmunition());

        //try to convert with resources and damaged shield
        IResource[] required = new IResource[]{
                new BlackResource(),
                new BlueResource(),
        };
        ship.getCargo().loadResources(required);
        assertTrue(logic.loadOneAmmunition());
    }

    @Test
    void chargeOneFuel() throws OutOfFuelException {
        Data.getInstance().setShip(new MilitaryShip());
        Ship ship = Data.getInstance().getShip();
        ConvertLogic logic = new ConvertLogic();

        //try to convert with full fuel loaded
        assertFalse(logic.chargeOneFuel());

        //try to convert without resources
        ship.consumeFuel(1);
        assertFalse(logic.chargeOneFuel());

        IResource[] required = new IResource[]{
                new BlackResource(),
                new BlueResource(),
                new GreenResource()
        };

        ship.getCargo().loadResources(required);
        assertTrue(logic.chargeOneFuel());
    }
}