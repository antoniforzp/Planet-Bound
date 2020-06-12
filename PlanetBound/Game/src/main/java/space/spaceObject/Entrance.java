package space.spaceObject;

import exceptions.OutOfFuelException;
import game.singletons.Data;

import java.io.Serializable;

public class Entrance implements ISpaceObject, Serializable {
    
    @Override
    public boolean consumeShip() throws OutOfFuelException {
        return Data.getInstance().getShip().consumeFuel(1);
    }
}
