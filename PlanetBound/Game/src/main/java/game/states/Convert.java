package game.states;

import exceptions.WrongArgumentException;
import config.Logger;
import game.State;
import game.singletons.Data;
import logic.ConvertLogic;

public class Convert extends State {

    private final ConvertLogic logic;

    public Convert() {
        logic = new ConvertLogic();
    }

    @Override
    public State convert(int choice) throws WrongArgumentException {

        switch (choice) {
            case 1: {
                Data.getInstance().setCanConvert(logic.chargeShieldCell());
                Logger.log("Resources converted to charge cell");
            }
            break;
            case 2: {
                Data.getInstance().setCanConvert(logic.loadOneAmmunition());
                Logger.log("Resources converted to load one ammunition");
            }
            break;
            case 3: {
                Data.getInstance().setCanConvert(logic.chargeOneFuel());
                Logger.log("Resources converted to charge one fuel");
            }
            break;
            default: {
                throw new WrongArgumentException();
            }
        }
        return new Convert();
    }

    @Override
    public State finish() {

        Logger.log("Returned to travelling in space");
        return new WaitInSpace();
    }
}
