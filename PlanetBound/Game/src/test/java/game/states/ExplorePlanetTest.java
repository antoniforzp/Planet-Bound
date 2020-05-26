package game.states;

import exceptions.UnavailableException;
import game.Game;
import logic.ExplorePlanetLogic;
import logic.singleton.LogicConfig;
import org.junit.jupiter.api.Test;
import ship.CrewMembers;
import ship.MiningShip;
import space.SpaceSector;
import walker.Coordinate;

import static org.junit.jupiter.api.Assertions.*;

class ExplorePlanetTest {

    @Test
    void moveTestExtractionPoint() throws UnavailableException {

        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(WaitInSpace.getInstance());

        LogicConfig.getInstance().setPosition(2);
        assertTrue(Game.dropOnSurface());

        ExplorePlanet state = (ExplorePlanet) Game.getState();
        ExplorePlanetLogic logic = state.getLogic();

        //block alien movement and place it outside the grid
        logic.setAlienMovement(false);

        Coordinate resCoordinate = LogicConfig.getInstance().getResourceCoordinate();
        System.out.println(resCoordinate);

        //set drone right next to resourceCoordinate
        LogicConfig.getInstance().getShip().getDrone().setPositionInitial(resCoordinate.getX() - 1, resCoordinate.getY());
        System.out.println(LogicConfig.getInstance().getShip().getDrone().getPosition());

        //move drone on resource position
        assertFalse(Game.move(resCoordinate.getX(), resCoordinate.getY()));
        assertTrue(LogicConfig.getInstance().isResourceTaken());

        Coordinate extCoordinate = LogicConfig.getInstance().getExtractionPoint();
        System.out.println(extCoordinate);

        //set drone right next to extractionPointCoordinate
        LogicConfig.getInstance().getShip().getDrone().setPositionInitial(extCoordinate.getX() - 1, extCoordinate.getY());
        System.out.println(LogicConfig.getInstance().getShip().getDrone().getPosition());

        //move drone on extractionPoint position
        assertTrue(Game.move(extCoordinate.getX(), extCoordinate.getY()));

        IState currentState = Game.getState();
        assertEquals(currentState, Multiply.getInstance());
    }

    @Test
    void moveTestResourceAlienMet() throws UnavailableException {

        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(WaitInSpace.getInstance());

        LogicConfig.getInstance().setPosition(2);
        assertTrue(LogicConfig.getInstance().getShip().getCrew().contains(CrewMembers.LandingPartyOfficer));
        LogicConfig.getInstance().setSpaceSector(new SpaceSector());
        assertFalse(LogicConfig.getInstance().getPlanet().isEmpty());


        assertTrue(Game.dropOnSurface());

        ExplorePlanet state = (ExplorePlanet) Game.getState();
        ExplorePlanetLogic logic = state.getLogic();
        logic.setAlienMovement(true);

        LogicConfig.getInstance().setResourceCoordinate(new Coordinate(0, 0));

        Coordinate alienCoordinate = new Coordinate(2, 1);
        logic.setAlienCoordinate(alienCoordinate);
        assertEquals(LogicConfig.getInstance().getAlien().getPosition(), alienCoordinate);

        LogicConfig.getInstance().getShip().getDrone().setPositionInitial(1, 1);
        Game.move(alienCoordinate.getX() + 1, alienCoordinate.getY());

        IState currentState = Game.getState();
        assertEquals(currentState, Fight.getInstance());
    }

    @Test
    void moveTestResourceTaken() throws UnavailableException {

        LogicConfig.getInstance().setShip(new MiningShip());
        Game.setState(WaitInSpace.getInstance());

        LogicConfig.getInstance().setPosition(2);
        assertTrue(Game.dropOnSurface());

        ExplorePlanet state = (ExplorePlanet) Game.getState();
        ExplorePlanetLogic logic = state.getLogic();
        logic.setAlienMovement(false);

        Coordinate resCoordinate = LogicConfig.getInstance().getResourceCoordinate();
        System.out.println(resCoordinate);

        LogicConfig.getInstance().getShip().getDrone().setPositionInitial(resCoordinate.getX() - 1, resCoordinate.getY());
        System.out.println(LogicConfig.getInstance().getShip().getDrone().getPosition());

        assertFalse(Game.move(resCoordinate.getX(), resCoordinate.getY()));
        assertTrue(LogicConfig.getInstance().isResourceTaken());
    }
}