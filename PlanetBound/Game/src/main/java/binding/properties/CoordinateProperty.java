package binding.properties;

import binding.Property;
import walker.Coordinate;

public class CoordinateProperty extends Property {

    Coordinate coordinate;

    public CoordinateProperty() {
        super();
        this.coordinate = new Coordinate(-1, -1);
    }

    public CoordinateProperty(Coordinate value) {
        super();
        this.coordinate = value;
    }

    public Coordinate getValue() {
        return coordinate;
    }

    public void setValue(Coordinate coordinate) {

        try {
            Coordinate oldVal = (Coordinate) this.coordinate.clone();
            this.coordinate = coordinate;
            callHandlers(oldVal, this.coordinate);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void setInitialXY(int x, int y) {

        try {
            Coordinate oldVal = (Coordinate) this.coordinate.clone();
            this.coordinate.setX(x);
            this.coordinate.setY(y);
            
            callHandlers(oldVal, this.coordinate);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void setX(int x) {

        try {
            Coordinate oldVal = (Coordinate) this.coordinate.clone();
            this.coordinate.setX(x);
            callHandlers(oldVal, this.coordinate);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void setY(int y) {
        try {
            Coordinate oldVal = (Coordinate) this.coordinate.clone();
            this.coordinate.setY(y);
            callHandlers(oldVal, this.coordinate);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return coordinate.getX();
    }

    public int getY() {
        return coordinate.getY();
    }
}
