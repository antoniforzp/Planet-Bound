package space.planet.planets;

import resources.*;
import resources.types.GreenResource;
import resources.types.RedResource;
import space.planet.Planet;

import java.util.Arrays;

public class GreenPlanet extends Planet {

    public GreenPlanet() {
        super();

        IResource[] resourcesAvailable = new IResource[]{new RedResource(), new GreenResource()};
        resources.addAll(Arrays.asList(resourcesAvailable));
    }
}
