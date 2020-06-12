package ship.cargo;

import binding.properties.ArrayProperty;
import binding.properties.IntegerProperty;
import resources.*;
import resources.types.*;

public class BasicCargo extends Cargo {

    public BasicCargo() {
        super();
        this.maxLevel = new IntegerProperty(1);
        this.level = new IntegerProperty(0);

        //on the board is 1-6
        blacks = new ArrayProperty<>(new BlackResource[5]);
        blues = new ArrayProperty<>(new BlueResource[5]);
        greens = new ArrayProperty<>(new GreenResource[5]);
        reds = new ArrayProperty<>(new RedResource[5]);
        artefacts = new ArrayProperty<>(new Artefact[5]);
    }

    //ICARGO INTERFACE REST OF THE IMPLEMENTATION

    public boolean upgrade(int level) {

        if (level == 1 && this.level.getValue() != level) {

            IResource[] temp = blacks.getArray();
            blacks.setNewArray(new BlackResource[11]);
            addAll(temp, blacks.getArray());

            temp = blues.getArray();
            blues.setNewArray(new BlueResource[11]);
            addAll(temp, blues.getArray());

            temp = greens.getArray();
            greens.setNewArray(new GreenResource[11]);
            addAll(temp, greens.getArray());

            temp = reds.getArray();
            reds.setNewArray(new RedResource[11]);
            addAll(temp, reds.getArray());

            this.level.setValue(level);

            return true;
        } else {
            return false;
        }
    }

}
