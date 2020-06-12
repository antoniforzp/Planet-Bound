package ship;

import binding.properties.IntegerProperty;
import ship.cargo.AdvancedCargo;
import ship.shield.Shield;
import ship.weapons.BasicWeaponSystem;
import walker.miningDrone.MiningDrone;

public class MiningShip extends Ship {

    public MiningShip() {
        super();

        this.fuel = new IntegerProperty(53);
        this.maxFuel = new IntegerProperty(53);
        this.shield = new Shield(18);
        this.weapon = new BasicWeaponSystem();
        this.cargo = new AdvancedCargo();
        this.drone = new MiningDrone();
    }

    //TO STRING

    @Override
    public String toString() {
        return ">>MINING SHIP<<\n" + super.toString();
    }
}
