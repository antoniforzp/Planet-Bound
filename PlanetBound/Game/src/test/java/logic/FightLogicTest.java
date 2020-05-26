package logic;

import exceptions.UnavailableException;
import exceptions.WrongArgumentException;
import game.Game;
import game.states.WaitInSpace;
import logic.singleton.LogicConfig;
import org.junit.jupiter.api.Test;
import ship.MilitaryShip;
import space.planet.PlanetFactory;
import walker.alien.aliens.testing.BadassAlien;
import walker.alien.aliens.testing.WeakAlien;
import walker.miningDrone.MiningDrone;

import static org.junit.jupiter.api.Assertions.*;

class FightLogicTest {

    @Test
    void fightTestLooseCondition() throws UnavailableException {

        LogicConfig.getInstance().setShip(new MilitaryShip());
        LogicConfig.getInstance().setPlanet(PlanetFactory.getRandomPlanet());

        Game.setState(WaitInSpace.getInstance());
        Game.dropOnSurface();

        FightLogic logic = new FightLogic();

        MiningDrone drone = LogicConfig.getInstance().getShip().getDrone();
        BadassAlien alien = new BadassAlien();

        assertFalse(logic.fight(drone, alien));

    }

    @Test
    void fightTestWinCondition() throws UnavailableException, WrongArgumentException {

        LogicConfig.getInstance().setShip(new MilitaryShip());
        LogicConfig.getInstance().setPlanet(PlanetFactory.getRandomPlanet());

        Game.setState(WaitInSpace.getInstance());
        Game.dropOnSurface();

        FightLogic logic = new FightLogic();

        MiningDrone drone = LogicConfig.getInstance().getShip().getDrone();
        WeakAlien alien = new WeakAlien();

        assertTrue(logic.fight(drone, alien));
    }
}