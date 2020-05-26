package space.spaceObject;

import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;

public interface ISpaceObject {
    boolean consumeShip() throws OutOfFuelException, CaptainDeletedException;
}
