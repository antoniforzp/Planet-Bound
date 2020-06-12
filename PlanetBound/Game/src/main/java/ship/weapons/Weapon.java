package ship.weapons;

import binding.properties.IntegerProperty;
import config.Logger;
import game.singletons.Data;

import java.io.Serializable;

public abstract class Weapon implements Serializable {

    protected IntegerProperty capacity;
    protected IntegerProperty ammunition;

    public Weapon() {
//        Data.getInstance().getBinder().addProperty(capacity);
//        Data.getInstance().getBinder().addProperty(ammunition);
    }

    public int getAmmunition() {
        return ammunition.getValue();
    }

    public int getCapacity() {
        return capacity.getValue();
    }

    public IntegerProperty capacityIntegerProperty(){
        return capacity;
    }

    public IntegerProperty ammunitionIntegerProperty(){
        return ammunition;
    }

    //USABILITY

    public boolean loadOneAmmunition() {

        if (ammunition.getValue() < capacity.getValue()) {
            ammunition.setValue(ammunition.getValue() + 1);

            Logger.log("One ammunition charged");
            return true;
        }
        return false;
    }

    public void fire(int amount) {
        int check = ammunition.getValue();

        check -= amount;
        if (check <= 0) {
            ammunition.setValue(0);
            return;
        } else {
            int temp = ammunition.getValue();
            ammunition.setValue(temp - amount);
        }

        Logger.log("Weapon fired: " + amount);
    }
}
