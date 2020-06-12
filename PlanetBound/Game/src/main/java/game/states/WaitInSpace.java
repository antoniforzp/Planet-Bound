package game.states;

import config.Logger;
import game.*;
import game.singletons.Data;
import logic.ExplorePlanetLogic;
import ship.CrewMembers;
import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import logic.WaitInSpaceLogic;

import java.util.List;

public class WaitInSpace extends State {

    private final WaitInSpaceLogic waitLogic;

    public WaitInSpace() {
        this.waitLogic = new WaitInSpaceLogic();
    }

    @Override
    public State startConvert() {

        List<CrewMembers> crew = Data.getInstance().getShip().getCrew();
        Data.getInstance().setCanStartConvert(crew.contains(CrewMembers.CargoOfficer));

        if (Data.getInstance().isCanStartConvert()) {
            Logger.log("Entered convert section");
            return new Convert();
        }

        Logger.log("Cannot enter convert section");
        return new WaitInSpace();
    }

    @Override
    public State startUpgrade() {

        Data.getInstance().setCanStartUpgrade(
                Data.getInstance().getPlanet().withSpaceStation() &&
                        Data.getInstance().getPosition() == 2);

        if (Data.getInstance().isCanStartUpgrade()) {
            Logger.log("Entered upgrade section");
            return new Upgrade();
        }

        Logger.log("Cannot enter upgrade section");
        return new WaitInSpace();
    }

    @Override
    public State dropOnSurface() {

        System.out.println("pos   : " + (Data.getInstance().getPosition() == 1));
        System.out.println("crew  : " + Data.getInstance().getShip().getCrew().contains(CrewMembers.LandingPartyOfficer));
        System.out.println("n visi: " + !Data.getInstance().isPlanetVisited());

        Data.getInstance().setCanDropOnSurface(
                Data.getInstance().getPosition() == 1 &&
                        Data.getInstance().getShip().getCrew().contains(CrewMembers.LandingPartyOfficer)
                        && !Data.getInstance().isPlanetVisited()
        );

        if (Data.getInstance().isCanDropOnSurface()) {
            ExplorePlanetLogic logic = new ExplorePlanetLogic();
            logic.setUpDropOnPlanet();
            Data.getInstance().setPlanetVisited(true);

            Logger.log("Dropped on planet");
            return new ExplorePlanet();
        }

        Logger.log("Cannot drop on planet");
        return new WaitInSpace();
    }

    //USABILITY
    @Override
    public State travel() {

        try {

            Data.getInstance().setEventMet(waitLogic.travel());
            if (Data.getInstance().isEventMet()) {

                Logger.log("Ship encountered an event");
                return new Event();
            }

            Logger.log("Ship travelled in space. Pos: " + Data.getInstance().getPosition());
            return new WaitInSpace();

        } catch (CaptainDeletedException | OutOfFuelException e) {
            Logger.log("Game over");
            return new GameOver();
        }
    }
}