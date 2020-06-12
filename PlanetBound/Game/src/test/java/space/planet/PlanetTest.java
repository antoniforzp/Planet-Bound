package space.planet;

import game.singletons.Data;
import org.junit.jupiter.api.Test;
import space.planet.planets.BlackPlanet;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    @Test
    void isEmpty() {
        Data.getInstance().setPlanet(new BlackPlanet());
        Planet planet = Data.getInstance().getPlanet();

        System.out.println(planet.getRandomResource());
        assertFalse(planet.isEmpty());
        System.out.println(planet.getRandomResource());
        assertTrue(planet.isEmpty());
    }

    @Test
    void withSpaceStation() {
        Data.getInstance().setPlanet(new BlackPlanet());
        Data.getInstance().getPlanet().setStation();
        assertTrue(Data.getInstance().getPlanet().withSpaceStation());
    }
}