package game.states;

import exceptions.*;
import game.Game;
import logic.EventLogic;
import logic.singleton.LogicConfig;

public class Event implements IState {

    protected static IState instance;
    private final EventLogic logic;

    public static IState getInstance() {
        if (instance == null)
            instance = new Event();
        return instance;
    }

    public Event() {
        this.logic = new EventLogic();
    }

    public EventLogic getLogic() {
        return logic;
    }

    @Override
    public boolean chooseShip(int choice) throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean startConvert() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean convert(int choice) throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean startUpgrade() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean upgrade(int choice) throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean finish() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean dropOnSurface() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean fight() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean move(int x, int y) throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean travel() throws UnavailableException {
        throw new UnavailableException();
    }

    //USABILITY
    @Override
    public boolean processEvent(int choice) throws WrongArgumentException {

        logic.setEventId(choice);
        try {
            logic.processEvent();
            Game.setState(WaitInSpace.getInstance());

        } catch (CaptainDeletedException e) {
            Game.setState(GameOver.getInstance());
        } catch (OutOfFuelException e) {
            LogicConfig.getInstance().setRunOutOfFuel(true);
            Game.setState(Convert.getInstance());
        }
        return true;
    }
}
