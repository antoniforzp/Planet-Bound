package walker;

import binding.observer.IObserver;
import binding.properties.CoordinateProperty;
import game.singletons.Data;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Walker implements Serializable {

    private final CoordinateProperty destination = new CoordinateProperty(new Coordinate(0, 0));
    private final CoordinateProperty position = new CoordinateProperty(new Coordinate(0, 0));

    public Walker() {
//        Data.getInstance().getBinder().addProperty(destination);
//        Data.getInstance().getBinder().addProperty(position);
    }

    public void setDestination(Coordinate destination) {
        this.destination.setValue(destination);
    }

    public Coordinate getPosition() {
        return position.getValue();
    }

    public CoordinateProperty positionCoordinateProperty() {
        return position;
    }

    public void setPositionInitial(int x, int y) {
        position.setValue(new Coordinate(x, y));
    }

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

    public void moveOwn(int x, int y) {

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

//            check grid
            if (x >= 0 && x <= 5 && y >= 0 && y <= 5) {
                position.setX(x);
                position.setY(y);
            }
        }
    }
}
