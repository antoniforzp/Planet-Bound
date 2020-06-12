package logic;

import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import game.singletons.Data;
import space.spaceObject.ISpaceObject;
import space.SpaceSector;
import space.spaceObject.Event;

import java.io.Serializable;

public class WaitInSpaceLogic implements Serializable {


    public boolean setPosition(int position) {
        Data.getInstance().setPosition(position);

        SpaceSector spaceSector = Data.getInstance().getSpaceSector();
        ISpaceObject currentPlace = spaceSector.getObjects()[position];

        return currentPlace.getClass() == Event.class;
    }

    public boolean travel() throws OutOfFuelException, CaptainDeletedException {
        boolean check = false;
        int position = Data.getInstance().getPosition();
        SpaceSector spaceSector = Data.getInstance().getSpaceSector();

        //after exceeding while array of space objects generate new space sector
        ++position;
        if (position > spaceSector.getObjects().length - 1) {

            setPosition(0);
            position = Data.getInstance().getPosition();

            Data.getInstance().setSpaceSector(new SpaceSector());
        } else {
            setPosition(position);
        }

        //check event
        ISpaceObject currentPlace = spaceSector.getObjects()[position];
        if (currentPlace.getClass() == Event.class) {
            check = true;
        }

        currentPlace.consumeShip();
        return check;
    }
}
