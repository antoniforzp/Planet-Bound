package ship.shield;

public interface IShield {

    int getCells();

    int getCapacity();

    boolean isActive();

    boolean chargeCell();

    /**
     * @param amount how many damage shield must consume
     * @return state of shield if it has been destroyed or not
     */
    boolean takeDamage(int amount);
}
