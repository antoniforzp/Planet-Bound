package space.spaceObject;

import ship.CrewMembers;
import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import logic.singleton.LogicConfig;
import ship.Ship;

public class Wormhole implements ISpaceObject {

    @Override
    public boolean consumeShip() throws OutOfFuelException, CaptainDeletedException {

        int fuelCost = 3;
        int shieldCost = 2;
        Ship ship = LogicConfig.getInstance().getShip();

        int shields = LogicConfig.getInstance().getShip().getShield().getCells();

        if (shields < 2) {
            LogicConfig.getInstance().getShip().looseCrewMember();
        }
        if (!ship.getCrew().contains(CrewMembers.ShieldOfficer)) {
            fuelCost += 1;
        }

        LogicConfig.getInstance().getShip().consumeFuel(fuelCost);
        LogicConfig.getInstance().getShip().getShield().takeDamage(shieldCost);

        return true;
    }
}
