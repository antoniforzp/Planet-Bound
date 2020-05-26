package ship.weapons;

public interface IWeapon {
    /**
     * @param amount of ammunition needed
     * @return the result of the operation (if ammunition left)
     */
    boolean fire(int amount);

    /**
     * @return ammunition
     */
    int getAmmunition();

    /**
     * @return maximum ammunition capacity
     */
    int getCapacity();

    /**
     * @return the status of the weapon system (active, nonactive)
     */
    boolean isActive();

    boolean loadOneAmmunition();
}
