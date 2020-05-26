package resources.types;

import resources.IResource;
import resources.ResourceType;

public class Artefact implements IResource {
    ResourceType type;

    public Artefact() {
        this.type = ResourceType.Artifact;
    }

    @Override
    public ResourceType getType() {
        return type;
    }
}
