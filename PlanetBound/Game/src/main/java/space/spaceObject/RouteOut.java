package space.spaceObject;

import exceptions.OutOfFuelException;
import logic.singleton.LogicConfig;

public class RouteOut implements ISpaceObject {

    @Override
    public boolean consumeShip() throws OutOfFuelException {
        return LogicConfig.getInstance().getShip().consumeFuel(1);
    }
}
