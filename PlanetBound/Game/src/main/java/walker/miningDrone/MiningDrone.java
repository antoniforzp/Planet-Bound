package walker.miningDrone;

import observer.IObserver;
import walker.Walker;

import java.util.ArrayList;

public class MiningDrone extends Walker {
    private final boolean isActive;
    private final int shieldsCapacity;
    private int shields;


    public MiningDrone() {
        this.observers = new ArrayList<>();
        this.isActive = true;
        this.shieldsCapacity = 6;
        this.shields = 6;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getShields() {
        return shields;
    }

    public int getShieldsCapacity() {
        return shieldsCapacity;
    }

    public boolean rechargeShields() {
        shields = shieldsCapacity;
        notifyChange("droneShields");
        return true;
    }

    public boolean takeDamage(int amount) {
        int check = shields;

        check -= amount;
        if (check <= 0) {
            shields = 0;
            return false;
        } else {
            shields -= amount;
        }
        notifyChange("droneShields");
        return true;
    }
}
