package logic;

import logic.singleton.LogicConfig;
import resources.IResource;
import resources.types.Artefact;

import java.util.Arrays;

public class MultiplyLogic {

    public boolean multiply(int amount) {

        LogicConfig.getInstance().setAmount(amount);
        IResource resource = LogicConfig.getInstance().getResource();

        if (resource.getClass() == Artefact.class) {

            IResource[] artifact = new IResource[1];
            Arrays.fill(artifact, resource);

            LogicConfig.getInstance().getShip().getCargo().loadResources(artifact);

            Artefact[] shipArtefacts = LogicConfig.getInstance().getShip().getCargo().getArtifacts();
            return LogicConfig.getInstance().getShip().getCargo().getCapacity(shipArtefacts) >= 5;


        } else {
            IResource[] arr = new IResource[amount];
            Arrays.fill(arr, resource);

            LogicConfig.getInstance().getShip().getCargo().loadResources(arr);
        }
        return false;
    }
}
