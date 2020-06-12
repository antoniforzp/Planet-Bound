package space.planet;

import exceptions.OutOfFuelException;
import game.singletons.Data;
import resources.IResource;
import space.spaceObject.ISpaceObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class Planet implements IPlanet, ISpaceObject, Serializable {

    protected IResource currentResource;
    protected ArrayList<IResource> resources;
    protected boolean spaceStation;

    public Planet() {

        this.spaceStation = false;
        this.resources = new ArrayList<>();
    }

    public ArrayList<IResource> getResources() {
        return resources;
    }

    @Override
    public boolean isEmpty() {
        return resources.isEmpty();
    }

    @Override
    public boolean withSpaceStation() {
        return spaceStation;
    }

    @Override
    public IResource getRandomResource() {
        Random random = new Random();
        int index = random.nextInt(resources.size());

        currentResource = resources.get(index);
        resources.remove(currentResource);

        return currentResource;
    }

    @Override
    public void setStation() {
        spaceStation = true;
    }

    //ISpaceObject INTERFACE IMPLEMENTATION

    @Override
    public boolean consumeShip() throws OutOfFuelException {
        return Data.getInstance().getShip().consumeFuel(1);
    }
}