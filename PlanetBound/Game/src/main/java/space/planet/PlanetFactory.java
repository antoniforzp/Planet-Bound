package space.planet;

import space.planet.planets.RedPlanet;
import space.planet.planets.BlackPlanet;
import space.planet.planets.BluePlanet;
import space.planet.planets.GreenPlanet;

import java.util.Random;

public class PlanetFactory {

    static public Planet getRandomPlanet() {

        Planet planet = null;
        Random random = new Random();
        switch (random.nextInt(4)) {

            case 0: {
                planet = new BlackPlanet();
            }
            break;
            case 1: {
                planet = new RedPlanet();
            }
            break;
            case 2: {
                planet = new BluePlanet();
            }
            break;
            case 3: {
                planet = new GreenPlanet();
            }
            break;
            default: {
                System.out.println("WRONG PLANET ID");
            }
        }

        //generate space.planet 70% without station and 30% with one
        int pChance = random.nextInt((100 - 1) + 1) + 1;

        if (planet != null) {
            if (pChance <= 30) {
                planet.setStation();
            }
        }
        return planet;
    }
}
