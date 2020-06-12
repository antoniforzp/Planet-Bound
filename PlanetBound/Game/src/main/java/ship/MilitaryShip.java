package ship;

import binding.properties.IntegerProperty;
import ship.cargo.BasicCargo;
import ship.shield.Shield;
import ship.weapons.AdvancedWeaponSystem;
import ship.weapons.BasicWeaponSystem;
import walker.miningDrone.MiningDrone;

public class MilitaryShip extends Ship {

    public MilitaryShip() {
        super();

        this.maxFuel = new IntegerProperty(35);
        this.fuel = new IntegerProperty(35);
        this.shield = new Shield(9);
        this.weapon = new BasicWeaponSystem();
        this.cargo = new BasicCargo();
        this.drone = new MiningDrone();
    }

    public boolean upgradeWeapon() {
        int ammoLeft = weapon.getAmmunition();
        this.weapon = new AdvancedWeaponSystem(ammoLeft);
        return true;
    }

    //TO STRING

    @Override
    public String toString() {
        return ">>MILITARY SHIP<<\n" + super.toString();
    }
}
