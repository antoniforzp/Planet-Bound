package space.spaceObject;

import config.Logger;
import ship.CrewMembers;
import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import game.singletons.Data;
import ship.Ship;

import java.io.Serializable;

public class Wormhole implements ISpaceObject, Serializable {

    @Override
    public boolean consumeShip() throws OutOfFuelException, CaptainDeletedException {

        int fuelCost = 3;
        int shieldCost = 2;
        Ship ship = Data.getInstance().getShip();

        int shields = Data.getInstance().getShip().getShield().getCells();

        if (shields < 2) {
            Data.getInstance().getShip().looseCrewMember();
        }
        if (!ship.getCrew().contains(CrewMembers.ShieldOfficer)) {
            fuelCost += 1;
        }

        Data.getInstance().getShip().consumeFuel(fuelCost);
        Data.getInstance().getShip().getShield().takeDamage(shieldCost);

        Logger.log("Leaving space sector: Wormhole");
        return true;
    }
}
