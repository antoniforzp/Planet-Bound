package game.states;

import exceptions.WrongArgumentException;
import config.Logger;
import game.singletons.Data;
import game.State;
import ship.MilitaryShip;
import ship.MiningShip;

public class ChooseShip extends State {

    @Override
    public State chooseShip(int choice) throws WrongArgumentException {
        switch (choice) {
            case 1: {
                Data.getInstance().setShip(new MiningShip());
                Logger.log("Mining ship chosen");
            }
            break;
            case 2: {
                Data.getInstance().setShip(new MilitaryShip());
                Logger.log("Military ship chosen");
            }
            break;
            default: {
                throw new WrongArgumentException();
            }
        }
        return new WaitInSpace();
    }
}