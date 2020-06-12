package logic;

import game.singletons.Data;
import resources.IResource;
import resources.types.Artefact;

import java.io.Serializable;
import java.util.Arrays;

public class MultiplyLogic implements Serializable {

    public boolean multiply(int amount) {

        Data.getInstance().setAmount(amount);
        IResource resource = Data.getInstance().getResource();


        if (resource.getClass() == Artefact.class) {
            Data.getInstance().setAmount(1);
            Artefact[] artefact = new Artefact[]{
                    new Artefact()
            };
            Data.getInstance().getShip().getCargo().loadResources(artefact);

        } else {
            IResource[] arr = new IResource[amount];
            Arrays.fill(arr, resource);
            Data.getInstance().getShip().getCargo().loadResources(arr);
        }

        Artefact[] shipArtefacts = Data.getInstance().getShip().getCargo().getArtifacts();
        return Data.getInstance().getShip().getCargo().getCapacity(shipArtefacts) >= 5;
    }
}
