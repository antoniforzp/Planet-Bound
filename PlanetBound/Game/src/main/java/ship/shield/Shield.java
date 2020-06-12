package ship.shield;

import binding.properties.IntegerProperty;
import config.Logger;
import game.singletons.Data;

import java.io.Serializable;

public class Shield implements Serializable {

    protected IntegerProperty capacity;
    protected IntegerProperty cells;

    public Shield(int cells) {
        this.capacity = new IntegerProperty(cells);
        this.cells = new IntegerProperty(cells);

//        Data.getInstance().getBinder().addProperty(this.capacity);
//        Data.getInstance().getBinder().addProperty(this.cells);
    }

    public int getCells() {
        return cells.getValue();
    }

    public int getCapacity() {
        return capacity.getValue();
    }

    public IntegerProperty capacityIntegerProperty() {
        return capacity;
    }

    public IntegerProperty cellsIntegerProperty() {
        return cells;
    }

    //USABILITY

    public boolean chargeCell() {

        if (cells.getValue() < capacity.getValue()) {
            cells.setValue(cells.getValue() + 1);
            Logger.log("Ship one shield cell charged");
            return true;
        }
        return false;
    }

    public boolean takeDamage(int amount) {

        cells.setValue(cells.getValue() - amount);
        if (cells.getValue() < 0) {
            cells.setValue(0);

            Logger.log("Ship shield cells lost: " + amount);
            return false;
        }
        return true;
    }
}
