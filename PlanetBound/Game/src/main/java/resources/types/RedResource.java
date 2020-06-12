package resources.types;

import resources.IResource;
import resources.ResourceType;

import java.io.Serializable;

public class RedResource implements IResource, Serializable {
    ResourceType type;

    public RedResource() {
        this.type = ResourceType.Red;
    }

    @Override
    public ResourceType getType() {
        return type;
    }
}
