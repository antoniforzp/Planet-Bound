package game.states;

import logic.ExplorePlanetLogic;
import ship.CrewMembers;
import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import exceptions.UnavailableException;
import game.Game;
import logic.WaitInSpaceLogic;
import logic.singleton.LogicConfig;

import java.util.List;

public class WaitInSpace implements IState {

    static IState instance;
    private final WaitInSpaceLogic waitLogic;

    public static IState getInstance() {
        if (instance == null) {
            instance = new WaitInSpace();
        }
        return instance;
    }

    public WaitInSpace() {
        this.waitLogic = new WaitInSpaceLogic();
    }


    @Override
    public boolean chooseShip(int choice) throws UnavailableException {
        throw new UnavailableException();
    }

    //USABILITY
    @Override
    public boolean startConvert() {

        //enter ConvertResources state
        List<CrewMembers> crew = LogicConfig.getInstance().getShip().getCrew();
        boolean check = crew.contains(CrewMembers.CargoOfficer);

        if (check) {
            Game.setState(Convert.getInstance());
        } else {
            Game.setState(WaitInSpace.getInstance());
        }
        return check;
    }

    @Override
    public boolean convert(int choice) throws UnavailableException {
        throw new UnavailableException();
    }

    //USABILITY
    @Override
    public boolean startUpgrade() {

        boolean check = LogicConfig.getInstance().getPlanet().withSpaceStation() &&
                LogicConfig.getInstance().getPosition() == 2;

        if (check) {
            Game.setState(Upgrade.getInstance());
        } else {
            Game.setState(WaitInSpace.getInstance());
        }
        return check;
    }

    @Override
    public boolean upgrade(int choice) throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean finish() throws UnavailableException {
        throw new UnavailableException();
    }

    //USABILITY
    @Override
    public boolean dropOnSurface() {

        boolean check = !LogicConfig.getInstance().getPlanet().isEmpty() && LogicConfig.getInstance().getPosition() == 2 &&
                LogicConfig.getInstance().getShip().getCrew().contains(CrewMembers.LandingPartyOfficer);

        System.out.println(LogicConfig.getInstance().getPlanet().isEmpty());
        if (check) {
            ExplorePlanetLogic logic = new ExplorePlanetLogic();
            logic.setRandomlyObjects();
            Game.setState(ExplorePlanet.getInstance());
        } else {
            Game.setState(WaitInSpace.getInstance());
        }
        return check;
    }

    @Override
    public boolean fight() throws UnavailableException {
        throw new UnavailableException();
    }

    @Override
    public boolean move(int x, int y) throws UnavailableException {
        throw new UnavailableException();
    }

    //USABILITY
    @Override
    public boolean travel() {

        boolean check;
        try {
            check = waitLogic.travel();
            if (check) {
                Game.setState(Event.getInstance());
            } else {
                Game.setState(WaitInSpace.getInstance());
            }
            return check;


        } catch (CaptainDeletedException e) {
            Game.setState(GameOver.getInstance());
            return false;
        } catch (OutOfFuelException e) {
            Game.setState(Convert.getInstance());
            LogicConfig.getInstance().setRunOutOfFuel(true);
            return false;
        }
    }

    @Override
    public boolean processEvent(int choice) throws UnavailableException {
        throw new UnavailableException();
    }
}