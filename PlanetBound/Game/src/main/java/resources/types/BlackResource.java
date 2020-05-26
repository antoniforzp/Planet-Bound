package resources.types;

import resources.IResource;
import resources.ResourceType;

public class BlackResource implements IResource {
    ResourceType type;

    public BlackResource() {
        this.type = ResourceType.Black;
    }

    @Override
    public ResourceType getType() {
        return type;
    }
}
