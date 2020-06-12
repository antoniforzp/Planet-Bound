package game.states;

import game.singletons.Data;
import game.Game;
import game.IState;
import logic.ExplorePlanetLogic;
import org.junit.jupiter.api.Test;
import ship.CrewMembers;
import ship.MiningShip;
import space.SpaceSector;
import walker.Coordinate;

import static org.junit.jupiter.api.Assertions.*;

class ExplorePlanetTest {

    @Test
    void moveTestExtractionPoint() {

        Data.getInstance().setShip(new MiningShip());
        Game.getInstance().setState(new WaitInSpace());

        Data.getInstance().setPosition(2);
        Game.getInstance().dropOnSurface();

        ExplorePlanet state = (ExplorePlanet) Game.getInstance().getState();
        ExplorePlanetLogic logic = state.getLogic();

        //block alien movement and place it outside the grid
        logic.setAlienMovement(false);

        Coordinate resCoordinate = Data.getInstance().getResourceCoordinate();
        System.out.println(resCoordinate);

        //set drone right next to resourceCoordinate
        Data.getInstance().getShip().getDrone().setPositionInitial(resCoordinate.getX() - 1, resCoordinate.getY());
        System.out.println(Data.getInstance().getShip().getDrone().getPosition());

        //move drone on resource position
        Game.getInstance().move(resCoordinate.getX(), resCoordinate.getY());
        assertTrue(Data.getInstance().isResourceTaken());

        Coordinate extCoordinate = Data.getInstance().getExtractionPoint();
        System.out.println(extCoordinate);

        //set drone right next to extractionPointCoordinate
        Data.getInstance().getShip().getDrone().setPositionInitial(extCoordinate.getX() - 1, extCoordinate.getY());
        System.out.println(Data.getInstance().getShip().getDrone().getPosition());

        //move drone on extractionPoint position
        Game.getInstance().move(extCoordinate.getX(), extCoordinate.getY());

        IState currentState = Game.getInstance().getState();
        assertEquals(Multiply.class, currentState.getClass());
    }

    @Test
    void moveTestResourceAlienMet() {

        Data.getInstance().setShip(new MiningShip());
        Game.getInstance().setState(new WaitInSpace());

        Data.getInstance().setPosition(2);
        assertTrue(Data.getInstance().getShip().getCrew().contains(CrewMembers.LandingPartyOfficer));
        Data.getInstance().setSpaceSector(new SpaceSector());
        assertFalse(Data.getInstance().getPlanet().isEmpty());


        Game.getInstance().dropOnSurface();

        ExplorePlanet state = (ExplorePlanet) Game.getInstance().getState();
        ExplorePlanetLogic logic = state.getLogic();
        logic.setAlienMovement(true);

        Data.getInstance().setResourceCoordinate(new Coordinate(0, 0));

        Coordinate alienCoordinate = new Coordinate(2, 1);
        logic.setAlienCoordinate(alienCoordinate);
        assertEquals(Data.getInstance().getAlien().getPosition(), alienCoordinate);

        Data.getInstance().getShip().getDrone().setPositionInitial(1, 1);
        Game.getInstance().move(alienCoordinate.getX() + 1, alienCoordinate.getY());

        IState currentState = Game.getInstance().getState();

        assertEquals(Fight.class, currentState.getClass());
    }

    @Test
    void moveTestResourceTaken() {

        Data.getInstance().setShip(new MiningShip());
        Data.getInstance().setSpaceSector(new SpaceSector());
        Game.getInstance().setState(new WaitInSpace());

        Data.getInstance().setPosition(2);
        Game.getInstance().dropOnSurface();

        ExplorePlanet state = (ExplorePlanet) Game.getInstance().getState();
        ExplorePlanetLogic logic = state.getLogic();
        logic.setAlienMovement(false);

        Coordinate resCoordinate = Data.getInstance().getResourceCoordinate();
        System.out.println(resCoordinate);

        Data.getInstance().getShip().getDrone().setPositionInitial(resCoordinate.getX() - 1, resCoordinate.getY());
        System.out.println(Data.getInstance().getShip().getDrone().getPosition());

        Game.getInstance().move(resCoordinate.getX(), resCoordinate.getY());
        assertTrue(Data.getInstance().isResourceTaken());
    }
}