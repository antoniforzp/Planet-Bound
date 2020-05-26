package resources.types;

import resources.IResource;
import resources.ResourceType;

public class RedResource implements IResource {
    ResourceType type;

    public RedResource() {
        this.type = ResourceType.Red;
    }

    @Override
    public ResourceType getType() {
        return type;
    }
}
