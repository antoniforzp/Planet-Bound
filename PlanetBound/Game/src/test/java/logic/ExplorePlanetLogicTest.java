package logic;

import game.singletons.Data;
import org.junit.jupiter.api.Test;
import ship.MiningShip;
import space.planet.PlanetFactory;
import walker.alien.AlienFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ExplorePlanetLogicTest {


    @Test
    void travelTest() {

        Data.getInstance().setShip(new MiningShip());
        Data.getInstance().setPlanet(PlanetFactory.getRandomPlanet());
        Data.getInstance().setAlien(AlienFactory.getRandomAlien());

        ExplorePlanetLogic logic = new ExplorePlanetLogic();

        Data.getInstance().getAlien().setPositionInitial(1, 1);
        Data.getInstance().getShip().getDrone().setPositionInitial(1, 1);

        //met alien
        assertFalse(logic.move(1, 1));

        //alien not met
        assertFalse(logic.move(2, 2));
    }
}