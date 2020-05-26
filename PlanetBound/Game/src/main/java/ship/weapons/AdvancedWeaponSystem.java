package ship.weapons;

import java.util.ArrayList;

public class AdvancedWeaponSystem extends Weapon {

    public AdvancedWeaponSystem() {
        this.capacity = 5;
        this.isActive = true;
        this.observers = new ArrayList<>();
    }

    public AdvancedWeaponSystem(int ammunitionLeft) {
        this.capacity = 18;
        this.ammunition = ammunitionLeft;
        this.isActive = true;
    }

    @Override
    public String toString() {
        return "Advan W/System";
    }
}
