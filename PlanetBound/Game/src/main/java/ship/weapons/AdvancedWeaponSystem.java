package ship.weapons;

import binding.properties.IntegerProperty;

public class AdvancedWeaponSystem extends Weapon {

    public AdvancedWeaponSystem() {
        this.capacity = new IntegerProperty(5);
    }

    public AdvancedWeaponSystem(int ammunitionLeft) {
        super();
        this.capacity = new IntegerProperty(18);
        this.ammunition = new IntegerProperty(ammunitionLeft);
    }

    @Override
    public String toString() {
        return "Advan W/System";
    }
}
