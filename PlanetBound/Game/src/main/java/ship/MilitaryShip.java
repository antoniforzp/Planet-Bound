package ship;

import ship.cargo.BasicCargo;
import ship.shield.Shield;
import ship.weapons.AdvancedWeaponSystem;
import ship.weapons.BasicWeaponSystem;
import walker.miningDrone.MiningDrone;

public class MilitaryShip extends Ship {

    public MilitaryShip() {
        super();

        this.maxFuel = 35;
        this.fuel = 35;
        this.shield = new Shield(9);
        this.weapon = new BasicWeaponSystem();
        this.cargo = new BasicCargo();
        this.drone = new MiningDrone();
    }

    public boolean upgradeWeapon() {
        int ammoLeft = weapon.getAmmunition();
        this.weapon = new AdvancedWeaponSystem(ammoLeft);
        notifyChange("ammunition");
        return true;
    }

    //TO STRING

    @Override
    public String toString() {
        return ">>MILITARY SHIP<<\n" + super.toString();
    }
}
