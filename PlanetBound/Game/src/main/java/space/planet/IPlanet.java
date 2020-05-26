package space.planet;

import resources.IResource;

public interface IPlanet {
    IResource getRandomResource();

    void setStation();

    boolean withSpaceStation();

    boolean isEmpty();
}
