package game.states;

import exceptions.*;
import config.Logger;
import game.State;
import logic.EventLogic;

public class Event extends State {

    private final EventLogic logic;

    public Event() {
        this.logic = new EventLogic();
    }

    public EventLogic getLogic() {
        return logic;
    }

    @Override
    public State processEvent(int choice) throws WrongArgumentException {

        logic.setEventId(choice);
        try {
            logic.processEvent();
            Logger.log("no. " + choice + " event processed");
            return new WaitInSpace();

        } catch (CaptainDeletedException | OutOfFuelException e) {
            Logger.log("Game over");
            return new GameOver();
        }
    }
}
