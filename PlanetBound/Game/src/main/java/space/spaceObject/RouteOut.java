package space.spaceObject;

import exceptions.OutOfFuelException;
import config.Logger;
import game.singletons.Data;

import java.io.Serializable;

public class RouteOut implements ISpaceObject, Serializable {

    @Override
    public boolean consumeShip() throws OutOfFuelException {
        Logger.log("Leaving space sector: RouteOut");
        return Data.getInstance().getShip().consumeFuel(1);
    }
}
