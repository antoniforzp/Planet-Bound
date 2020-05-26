package logic;

import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import logic.singleton.LogicConfig;
import space.spaceObject.ISpaceObject;
import space.SpaceSector;
import space.spaceObject.Event;

public class WaitInSpaceLogic {


    public boolean setPosition(int position) {
        LogicConfig.getInstance().setPosition(position);

        SpaceSector spaceSector = LogicConfig.getInstance().getSpaceSector();
        ISpaceObject currentPlace = spaceSector.getObjects()[position];

        return currentPlace.getClass() == Event.class;
    }

    public boolean travel() throws OutOfFuelException, CaptainDeletedException {
        boolean check = false;
        int position = LogicConfig.getInstance().getPosition();
        SpaceSector spaceSector = LogicConfig.getInstance().getSpaceSector();

        //after exceeding while array of space objects generate new space sector
        ++position;
        if (position > spaceSector.getObjects().length - 1) {

            setPosition(0);
            position = LogicConfig.getInstance().getPosition();

            LogicConfig.getInstance().setSpaceSector(new SpaceSector());
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
