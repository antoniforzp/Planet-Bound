package ship.weapons;

import observer.IObservable;
import observer.IObserver;

import java.util.ArrayList;

public abstract class Weapon implements IWeapon, IObservable {

    protected ArrayList<IObserver> observers;

    protected int capacity;
    protected int ammunition;
    protected boolean isActive;

    @Override
    public int getAmmunition() {
        return ammunition;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    //USABILITY

    @Override
    public boolean loadOneAmmunition() {

        if (ammunition < capacity) {
            ammunition++;
            notifyChange("weapon");
            return true;
        }
        return false;
    }

    @Override
    public boolean fire(int amount) {
        int check = ammunition;

        check -= amount;
        if (check <= 0) {
            ammunition = 0;
            return false;
        } else {
            ammunition -= amount;
        }
        notifyChange("weapon");
        return true;
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
