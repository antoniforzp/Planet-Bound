package logic;

import logic.singleton.LogicConfig;
import resources.*;
import resources.types.BlackResource;
import resources.types.BlueResource;
import resources.types.GreenResource;
import ship.Ship;

public class ConvertLogic {

    private final Ship ship;

    public ConvertLogic() {
        this.ship = LogicConfig.getInstance().getShip();
    }

    //one shield cell = black + green + blue
    public boolean chargeShieldCell() {
        IResource[] required = new IResource[]{
                new BlackResource(),
                new GreenResource(),
                new BlueResource(),
        };
        //block the attempt to refill fully charged ship
        if (ship.getShield().getCells() < ship.getShield().getCapacity()) {
            if (ship.getCargo().contains(required)) {
                ship.getCargo().removeResources(required);
                return ship.getShield().chargeCell();
            }
        }
        return false;
    }

    //one ammunition charge = black + blue
    public boolean loadOneAmmunition() {
        IResource[] required = new IResource[]{
                new BlackResource(),
                new BlueResource(),
        };
        //block the attempt to refill fully charged ship
        if (ship.getWeapon().getAmmunition() < ship.getWeapon().getCapacity()) {
            if (ship.getCargo().contains(required)) {
                ship.getCargo().removeResources(required);

                return ship.getWeapon().loadOneAmmunition();
            }
        }
        return false;
    }

    //one fuel charge = black + blue + green
    public boolean chargeOneFuel() {
        IResource[] required = new IResource[]{
                new BlackResource(),
                new BlueResource(),
                new GreenResource()
        };

        //block the attempt to refill fully charged ship
        if (ship.getFuel() < ship.getMaxFuel()) {
            if (ship.getCargo().contains(required)) {
                ship.getCargo().removeResources(required);

                return ship.chargeOneFuel();
            }
        }
        return false;
    }
}
