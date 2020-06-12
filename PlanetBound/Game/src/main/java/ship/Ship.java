package ship;

import binding.properties.IntegerProperty;
import binding.properties.ListProperty;
import config.Logger;
import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import game.singletons.Data;
import ship.cargo.Cargo;
import ship.shield.Shield;
import ship.weapons.Weapon;
import walker.miningDrone.MiningDrone;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static ship.CrewMembers.*;

public abstract class Ship implements Serializable {

    //common
    protected ListProperty<CrewMembers> crew = new ListProperty<>();
    protected MiningDrone drone = new MiningDrone();

    //various
    protected IntegerProperty maxFuel;
    protected IntegerProperty fuel;

    protected Shield shield;
    protected Cargo cargo;
    protected Weapon weapon;

    protected Ship() {
        crew.addAll(Arrays.asList(CrewMembers.values()));
//        Data.getInstance().getBinder().addProperty(crew);
//        Data.getInstance().getBinder().addProperty(maxFuel);
//        Data.getInstance().getBinder().addProperty(fuel);
    }

    //GETTERS (SHIP COMPONENTS-EQUIPMENT)

    public Cargo getCargo() {
        return this.cargo;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public Shield getShield() {
        return this.shield;
    }

    public MiningDrone getDrone() {
        return this.drone;
    }

    //GETTERS (SHIP ATTRIBUTES)

    public int getMaxFuel() {
        return this.maxFuel.getValue();
    }

    public IntegerProperty maxFuelProperty() {
        return maxFuel;
    }


    public int getFuel() {
        return this.fuel.getValue();
    }

    public IntegerProperty fuelProperty() {
        return fuel;
    }


    public List<CrewMembers> getCrew() {
        return crew.getList();
    }

    public ListProperty<CrewMembers> crewMembersListProperty() {
        return crew;
    }

    public boolean consumeFuel(int amount) throws OutOfFuelException {
        int check = fuel.getValue();
        check -= amount;
        if (check <= 0) {
            fuel.setValue(0);
            throw new OutOfFuelException();
        } else {
            int updated = fuel.getValue() - amount;
            fuel.setValue(updated);
        }

        Logger.log("Ship fuel consumed: " + amount);
        return true;
    }

    public boolean chargeOneFuel() {
        if (fuel.getValue() < maxFuel.getValue()) {
            fuel.setValue(fuel.getValue() + 1);
            return true;
        }

        Logger.log("One ship fuel charged");
        return false;
    }

    public boolean setNewDrone() {
        drone = new MiningDrone();

        Logger.log("New drone set");
        return true;
    }

    public boolean addNewCrewMember() {
        CrewMembers member;
        if (crew.getList().size() >= 6) {
            return false;
        } else {
            switch (crew.getList().size()) {
                case 1:
                    member = NavigationOfficer;
                    break;
                case 2:
                    member = LandingPartyOfficer;
                    break;
                case 3:
                    member = CrewMembers.ShieldOfficer;
                    break;
                case 4:
                    member = WeaponOfficer;
                    break;
                case 5:
                    member = CargoOfficer;
                    break;
                default:
                    return false;
            }
        }
        crew.addElement(member);
        Logger.log("New member on ship: " + member);
        return true;
    }

    public boolean looseCrewMember() throws CaptainDeletedException {
        if (crew.getList().size() == 1) {
            throw new CaptainDeletedException();
        } else {
            int i = crew.getList().size() - 1;
            CrewMembers member = crew.removeElement(i);
            Logger.log("Crew member lost:  " + member);
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder strB = new StringBuilder();

        strB.append("CREW: ");
        for (CrewMembers c : crew.getList()) {
            strB.append(c).append(" ");
        }
        strB.append("\n");

        strB.append("FUEL:    ").append(fuel).append("/").append(maxFuel).append("\n");

        strB.append("SHIELD:  ").append(shield.getCells()).append("/").append(shield.getCapacity())
                .append("\n");

        strB.append("WEAPON:  ").append(weapon.getAmmunition()).append("/").append(weapon.getCapacity())
                .append(weapon).append("\n");

        strB.append("M.DRONE: ").append(drone.getShields()).append("/").append(drone.getShieldsCapacity()).append("\n");

        strB.append("CARGO:   ").append("\n");
        strB.append(cargo).append("\n");

        return strB.toString();
    }
}