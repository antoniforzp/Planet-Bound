package ship.cargo;

import resources.*;
import resources.types.*;

public interface ICargo {

    //USABILITY

    /**
     * @param level - for which level cargo will be upgraded
     * @return the result of the operation
     */
    boolean upgrade(int level);

    /**
     * @param resources the array of resources placed in the storage
     * @return the result of the operation
     */
    boolean loadResources(IResource[] resources);

    /**
     * @param resources - how much
     * @return result of the operation
     */
    boolean removeResources(IResource[] resources);

    /**
     * @param resources - the array of the various resources to check if there is the exact amount
     *                  made for upgrade purchase purpose
     * @return the boolean of contains
     */
    boolean contains(IResource[] resources);

    boolean checkEmpty(IResource[] resources);

    //EMPTY CHECKERS

    /**
     * @return return the boolean if there is any resource of certain type in the cargo
     */
    boolean isBlacksEmpty();

    boolean isBluesEmpty();

    boolean isGreensEmpty();

    boolean isRedsEmpty();

    //GETTERS

    int getLevel();

    BlackResource[] getBlacks();

    BlueResource[] getBlues();

    GreenResource[] getGreens();

    RedResource[] getReds();

    Artefact[] getArtifacts();

    int getCapacity(IResource[] resources);
}
