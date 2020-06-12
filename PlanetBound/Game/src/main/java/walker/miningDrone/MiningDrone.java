package walker.miningDrone;

import binding.properties.IntegerProperty;
import config.Logger;
import game.singletons.Data;
import walker.Walker;

import java.util.ArrayList;

public class MiningDrone extends Walker {
    private final IntegerProperty shieldsCapacity;
    private IntegerProperty shields;


    public MiningDrone() {
        super();

        this.shieldsCapacity = new IntegerProperty(6);
        this.shields = new IntegerProperty(6);
//
//        Data.getInstance().getBinder().addProperty(shieldsCapacity);
//        Data.getInstance().getBinder().addProperty(shields);
    }

    public int getShields() {
        return shields.getValue();
    }

    public int getShieldsCapacity() {
        return shieldsCapacity.getValue();
    }

    public IntegerProperty shieldsIntegerProperty() {
        return shields;
    }

    public IntegerProperty shieldsCapacityIntegerProperty() {
        return shieldsCapacity;
    }

    public boolean rechargeShields() {
        shields = shieldsCapacity;

        Logger.log("Drone has recharged all shields");
        return true;
    }

    public boolean takeDamage(int amount) {
        int check = shields.getValue();

        check -= amount;
        if (check <= 0) {
            shields.setValue(0);
            return false;
        } else {
            shields.setValue(shields.getValue() - amount);
        }

        Logger.log("Drone has taken damage: " + amount);
        return true;
    }
}
