package space.planet.planets;

import resources.types.BlackResource;
import resources.types.BlueResource;
import resources.IResource;
import space.planet.Planet;

import java.util.Arrays;

public class BlackPlanet extends Planet {

    public BlackPlanet() {
        super();

        IResource[] resourcesAvailable = new IResource[]{new BlackResource(), new BlueResource()};
        resources.addAll(Arrays.asList(resourcesAvailable));
    }
}
