package resources.types;

import resources.IResource;
import resources.ResourceType;

public class GreenResource implements IResource {
    ResourceType type;

    public GreenResource() {
        this.type = ResourceType.Green;
    }

    @Override
    public ResourceType getType() {
        return type;
    }
}
