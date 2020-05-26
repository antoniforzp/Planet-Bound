package logic;

import logic.singleton.LogicConfig;
import org.junit.jupiter.api.Test;
import ship.MiningShip;
import space.planet.PlanetFactory;
import walker.alien.AlienFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ExplorePlanetLogicTest {


    @Test
    void travelTest() {

        LogicConfig.getInstance().setShip(new MiningShip());
        LogicConfig.getInstance().setPlanet(PlanetFactory.getRandomPlanet());
        LogicConfig.getInstance().setAlien(AlienFactory.getRandomAlien());

        ExplorePlanetLogic logic = new ExplorePlanetLogic();

        LogicConfig.getInstance().getAlien().setPositionInitial(1,1);
        LogicConfig.getInstance().getShip().getDrone().setPositionInitial(1,1);

        //met alien
        assertFalse(logic.move(1, 1));

        //alien not met
        assertFalse(logic.move(2, 2));

    }
}