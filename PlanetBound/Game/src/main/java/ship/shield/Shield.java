package ship.shield;

import observer.IObservable;
import observer.IObserver;

import java.util.ArrayList;

public class Shield implements IShield, IObservable {

    protected ArrayList<IObserver> observers;

    protected int capacity;
    protected int cells;
    protected boolean isActive;

    public Shield(int cells) {
        this.capacity = cells;
        this.cells = cells;
        this.isActive = true;
        this.observers = new ArrayList<>();
    }

    @Override
    public int getCells() {
        return cells;
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
    public boolean chargeCell() {

        if (cells < capacity) {
            cells++;
            notifyChange("shield");
            return true;
        }
        return false;
    }

    @Override
    public boolean takeDamage(int amount) {

        cells -= amount;
        if (cells < 0) {
            cells = 0;
            return false;
        }
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
