package resources.types;

import resources.IResource;
import resources.ResourceType;

public class BlueResource implements IResource {
    ResourceType type;

    public BlueResource() {
        this.type = ResourceType.Blue;
    }

    @Override
    public ResourceType getType() {
        return type;
    }
}
