package logic;

import game.Game;
import game.states.WaitInSpace;
import game.singletons.Data;
import org.junit.jupiter.api.Test;
import ship.MilitaryShip;
import space.planet.PlanetFactory;
import walker.alien.aliens.testing.BadassAlien;
import walker.alien.aliens.testing.WeakAlien;
import walker.miningDrone.MiningDrone;

import static org.junit.jupiter.api.Assertions.*;

class FightLogicTest {

    @Test
    void fightTestLooseCondition() {

        Data.getInstance().setShip(new MilitaryShip());
        Data.getInstance().setPlanet(PlanetFactory.getRandomPlanet());

        Game.getInstance().setState(new WaitInSpace());
        Game.getInstance().dropOnSurface();

        FightLogic logic = new FightLogic();

        MiningDrone drone = Data.getInstance().getShip().getDrone();
        BadassAlien alien = new BadassAlien();

        assertFalse(logic.fight(drone, alien));

    }

    @Test
    void fightTestWinCondition() {

        Data.getInstance().setShip(new MilitaryShip());
        Data.getInstance().setPlanet(PlanetFactory.getRandomPlanet());

        Game.getInstance().setState(new WaitInSpace());
        Game.getInstance().dropOnSurface();

        FightLogic logic = new FightLogic();

        MiningDrone drone = Data.getInstance().getShip().getDrone();
        WeakAlien alien = new WeakAlien();

        assertTrue(logic.fight(drone, alien));
    }
}