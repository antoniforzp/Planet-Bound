package resources.types;

import resources.IResource;
import resources.ResourceType;

import java.io.Serializable;

public class BlueResource implements IResource, Serializable {
    ResourceType type;

    public BlueResource() {
        this.type = ResourceType.Blue;
    }

    @Override
    public ResourceType getType() {
        return type;
    }
}
