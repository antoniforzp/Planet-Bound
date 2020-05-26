package space.planet;

import logic.singleton.LogicConfig;
import org.junit.jupiter.api.Test;
import space.planet.planets.BlackPlanet;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    @Test
    void isEmpty() {
        LogicConfig.getInstance().setPlanet(new BlackPlanet());
        Planet planet = LogicConfig.getInstance().getPlanet();

        System.out.println(planet.getRandomResource());
        assertFalse(planet.isEmpty());
        System.out.println(planet.getRandomResource());
        assertTrue(planet.isEmpty());
    }

    @Test
    void withSpaceStation() {
        LogicConfig.getInstance().setPlanet(new BlackPlanet());
        LogicConfig.getInstance().getPlanet().setStation();
        assertTrue(LogicConfig.getInstance().getPlanet().withSpaceStation());
    }
}