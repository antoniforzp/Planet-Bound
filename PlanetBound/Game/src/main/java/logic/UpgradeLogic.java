package logic;

import game.singletons.Data;
import resources.IResource;
import resources.types.BlackResource;
import resources.types.BlueResource;
import resources.types.GreenResource;
import resources.types.RedResource;
import ship.MilitaryShip;
import ship.Ship;

import java.io.Serializable;

public class UpgradeLogic implements Serializable {

    //Service your landing craft to its full armor strength for one of each resource [red, black, green and blue]
    public boolean refillMiningDrone() {

        Ship ship = Data.getInstance().getShip();

        IResource[] required = new IResource[]{
                new RedResource(),
                new BlackResource(),
                new GreenResource(),
                new BlueResource(),
        };

        //block the attempt to refill fully charged drone
        if (ship.getDrone().getShields() < ship.getDrone().getShieldsCapacity()) {
            if (ship.getCargo().contains(required)) {
                return Data.getInstance().getShip().getDrone().rechargeShields();
            }
        }
        return false;
    }

    //Upgrade your cargo hold by one section, once per space station visit for two of each resource. [red, black, green and blue]
    public boolean upgradeCargoHold() {

        Ship ship = Data.getInstance().getShip();

        IResource[] required = new IResource[]{
                new RedResource(), new RedResource(),
                new BlackResource(), new BlackResource(),
                new GreenResource(), new GreenResource(),
                new BlueResource(), new BlueResource()
        };

        //check if ship contains required resources
        if (ship.getCargo().contains(required)) {

            ship.getCargo().removeResources(required);

            int currLevel = ship.getCargo().getLevel();
            return ship.getCargo().upgrade(++currLevel);
        }
        return false;
    }

    //Hire a single crew member that was lost for one of each resource [red, black, green and blue]
    public boolean hireNewMember() {

        Ship ship = Data.getInstance().getShip();

        IResource[] required = new IResource[]{
                new RedResource(),
                new BlackResource(),
                new GreenResource(),
                new BlueResource(),
        };

        //check if ship contains required resources
        if (ship.getCargo().contains(required)) {
            ship.getCargo().removeResources(required);
            return ship.addNewCrewMember();
        }
        return false;
    }

    //Upgrade your weapon system on the Military ship for two of each resource [red, black, green and blue]
    public boolean upgradeWeaponSystem() {

        Ship ship = Data.getInstance().getShip();

        if (ship.getClass() == MilitaryShip.class) {

            IResource[] required = new IResource[]{
                    new RedResource(), new RedResource(),
                    new BlackResource(), new BlackResource(),
                    new GreenResource(), new GreenResource(),
                    new BlueResource(), new BlueResource()
            };

            if (ship.getCargo().contains(required)) {
                return ((MilitaryShip) ship).upgradeWeapon();
            }
        }
        return false;
    }

    //Purchase a new mining drone for two of each resource [red, black, green and blue]
    public boolean buyNewMiningDrone() {

        Ship ship = Data.getInstance().getShip();

        IResource[] required = new IResource[]{
                new RedResource(), new RedResource(),
                new BlackResource(), new BlackResource(),
                new GreenResource(), new GreenResource(),
                new BlueResource(), new BlueResource()
        };

        if (ship.getCargo().contains(required)) {
            return ship.setNewDrone();
        }
        return false;
    }
}