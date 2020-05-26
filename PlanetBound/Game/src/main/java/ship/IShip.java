package ship;

import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;

interface IShip {

    //USABILITY

    /**
     * @param amount of the fuel to consume
     * @return the result of the operation (if any fuel left)
     */
    boolean consumeFuel(int amount) throws OutOfFuelException;

    /**
     * @return
     */
    boolean chargeOneFuel();

    /**
     * @return the result of the operation (if adding the member is available)
     */
    boolean addNewCrewMember();

    /**
     * @return the result of the operation
     */
    boolean setNewDrone();

    /**
     * Methods pops the lowest officer from crew list
     *
     * @return the result of the operation
     * @throws CaptainDeletedException if the last crew member(captain) is to be deleted
     */
    boolean looseCrewMember() throws CaptainDeletedException;
}
