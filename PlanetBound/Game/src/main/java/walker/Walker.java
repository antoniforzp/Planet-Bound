package walker;

import observer.IObservable;
import observer.IObserver;

import java.util.ArrayList;

public abstract class Walker implements IWalker, IObservable {

    protected ArrayList<IObserver> observers;

    private Coordinate destination = new Coordinate(0, 0);
    private Coordinate position = new Coordinate(0, 0);

    public void setDestination(Coordinate destination) {
        this.destination = destination;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPositionInitial(int x, int y) {
        position = new Coordinate(x, y);
    }

    @Override
    public void moveTowardsDestination() {

        int xDiff = destination.getX() - position.getX();
        int yDiff = destination.getY() - position.getY();

        if (xDiff == 0 && yDiff == 0) {
            position.setY(position.getY());
            position.setX(position.getX());
        } else {
            if (Math.abs(xDiff) > Math.abs(yDiff)) {

                position.setY(position.getY());
                if (xDiff > 0) {
                    position.setX(position.getX() + 1);
                } else {
                    position.setX(position.getX() - 1);
                }

            } else {

                position.setX(position.getX());
                if (yDiff > 0) {
                    position.setY(position.getY() + 1);
                } else {
                    position.setY(position.getY() - 1);
                }
            }
        }
    }

    @Override
    public boolean moveOwn(int x, int y) {

        if (
            //check up
                ((x == position.getX() && y == position.getY() - 1)) ||
                        //check down
                        (x == position.getX() && y == position.getY() + 1) ||
                        //check left
                        (x == position.getX() - 1 && y == position.getY()) ||
                        //check right
                        (x == position.getX() + 1 && y == position.getY())
        ) {
            position.setX(x);
            position.setY(y);
            return true;
        }
        return false;
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
