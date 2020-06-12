package ship.weapons;

import binding.properties.IntegerProperty;

public class BasicWeaponSystem extends Weapon {

    public BasicWeaponSystem() {
        super();
        this.capacity = new IntegerProperty(9);
        this.ammunition = new IntegerProperty(9);
    }
    
    @Override
    public String toString() {
        return "Basic W/System";
    }
}
