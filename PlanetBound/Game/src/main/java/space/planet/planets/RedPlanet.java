package space.planet.planets;

import resources.types.BlueResource;
import resources.IResource;
import resources.types.RedResource;
import space.planet.Planet;

import java.util.Arrays;

public class RedPlanet extends Planet {

    public RedPlanet() {
        super();

        IResource[] resourcesAvailable = new IResource[]{new RedResource(), new BlueResource()};
        resources.addAll(Arrays.asList(resourcesAvailable));
    }
}
