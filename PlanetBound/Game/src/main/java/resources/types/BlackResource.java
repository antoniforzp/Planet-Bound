package resources.types;

import resources.IResource;
import resources.ResourceType;

import java.io.Serializable;

public class BlackResource implements IResource, Serializable {
    ResourceType type;

    public BlackResource() {
        this.type = ResourceType.Black;
    }

    @Override
    public ResourceType getType() {
        return type;
    }
}
