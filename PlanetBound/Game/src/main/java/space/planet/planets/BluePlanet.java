package space.planet.planets;

import resources.*;
import resources.types.Artefact;
import resources.types.BlackResource;
import resources.types.BlueResource;
import resources.types.GreenResource;
import space.planet.Planet;

import java.util.Arrays;

public class BluePlanet extends Planet {

    public BluePlanet() {
        super();

        IResource[] resourcesAvailable = new IResource[]{new BlackResource(), new GreenResource(),
                new BlueResource(), new Artefact()};
        resources.addAll(Arrays.asList(resourcesAvailable));
    }
}
