package ship.weapons;

import java.util.ArrayList;

public class BasicWeaponSystem extends Weapon {

    public BasicWeaponSystem() {
        this.observers = new ArrayList<>();
        this.capacity = 9;
        this.ammunition = 9;
        this.isActive = true;
    }
    
    @Override
    public String toString() {
        return "Basic W/System";
    }
}
