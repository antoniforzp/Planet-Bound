package ship;

import exceptions.CaptainDeletedException;
import exceptions.OutOfFuelException;
import observer.IObservable;
import observer.IObserver;
import ship.cargo.Cargo;
import ship.shield.Shield;
import ship.weapons.Weapon;
import walker.miningDrone.MiningDrone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static ship.CrewMembers.*;

public abstract class Ship implements IShip, IObservable {

    //common
    protected ArrayList<IObserver> observers = new ArrayList<>();
    protected List<CrewMembers> crew = new LinkedList<>();
    protected MiningDrone drone = new MiningDrone();

    //various
    protected int maxFuel;
    protected int fuel;

    protected Shield shield;
    protected Cargo cargo;
    protected Weapon weapon;

    protected Ship() {
        crew.addAll(Arrays.asList(CrewMembers.values()));
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
        return this.maxFuel;
    }

    public int getFuel() {
        return this.fuel;
    }

    public List<CrewMembers> getCrew() {
        return crew;
    }

    //ISHIP INTERFACE IMPLEMENTATION

    @Override
    public boolean consumeFuel(int amount) throws OutOfFuelException {
        int check = fuel;
        check -= amount;
        if (check <= 0) {
            fuel = 0;
            throw new OutOfFuelException();
        } else {
            fuel -= amount;
        }
        notifyChange("fuel");
        return true;
    }

    @Override
    public boolean chargeOneFuel() {
        if (fuel < maxFuel) {
            fuel++;
            notifyChange("fuel");
            return true;
        }
        return false;
    }

    @Override
    public boolean setNewDrone() {
        drone = new MiningDrone();
        notifyChange("drone");
        return true;
    }

    @Override
    public boolean addNewCrewMember() {
        if (crew.size() >= 6) {
            return false;
        } else {
            switch (crew.size()) {
                case 1:
                    crew.add(NavigationOfficer);
                    break;
                case 2:
                    crew.add(LandingPartyOfficer);
                    break;
                case 3:
                    crew.add(CrewMembers.ShieldOfficer);
                    break;
                case 4:
                    crew.add(WeaponOfficer);
                    break;
                case 5:
                    crew.add(CargoOfficer);
                    break;
                default:
                    return false;
            }
        }
        notifyChange("crew");
        return true;
    }

    @Override
    public boolean looseCrewMember() throws CaptainDeletedException {
        if (crew.size() == 1) {
            throw new CaptainDeletedException();
        } else {
            int i = crew.size() - 1;
            crew.remove(i);
        }
        notifyChange("crew");
        return true;
    }

    @Override
    public String toString() {
        StringBuilder strB = new StringBuilder();

        strB.append("CREW: ");
        for (CrewMembers c : crew) {
            strB.append(c).append(" ");
        }
        strB.append("\n");

        strB.append("FUEL:    ").append(fuel).append("/").append(maxFuel).append("\n");

        strB.append("SHIELD:  ").append(shield.getCells()).append("/").append(shield.getCapacity())
                .append("\t active: ").append(shield.isActive()).append("\n");

        strB.append("WEAPON:  ").append(weapon.getAmmunition()).append("/").append(weapon.getCapacity())
                .append("\t active: ").append(weapon.isActive())
                .append("\t").append(weapon).append("\n");

        strB.append("M.DRONE: ").append(drone.getShields()).append("/").append(drone.getShieldsCapacity())
                .append("\t active: ").append(drone.isActive()).append("\n");

        strB.append("CARGO:   ").append("\n");
        strB.append(cargo).append("\n");

        return strB.toString();
    }


    //IOBSERVABLE INTERFACE IMPLEMENTATION

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyChange(String property) {
        for (IObserver o : observers) {
            o.update(property);
        }
    }
}