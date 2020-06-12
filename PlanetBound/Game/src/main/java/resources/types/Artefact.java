package resources.types;

import resources.IResource;
import resources.ResourceType;

import java.io.Serializable;

public class Artefact implements IResource, Serializable {
    ResourceType type;

    public Artefact() {
        this.type = ResourceType.Artifact;
    }

    @Override
    public ResourceType getType() {
        return type;
    }
}
