package resources.types;

import resources.IResource;
import resources.ResourceType;

import java.io.Serializable;

public class GreenResource implements IResource, Serializable {
    ResourceType type;

    public GreenResource() {
        this.type = ResourceType.Green;
    }

    @Override
    public ResourceType getType() {
        return type;
    }
}
