package ship.cargo;

import binding.properties.ArrayProperty;
import binding.properties.IntegerProperty;
import resources.types.*;

public class AdvancedCargo extends Cargo {

    public AdvancedCargo() {
        super();
        this.maxLevel = new IntegerProperty(3);
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

        BlackResource[] oldBlacks = blacks.getArray();
        BlueResource[] oldBlues = blues.getArray();
        GreenResource[] oldGreens = greens.getArray();
        RedResource[] oldReds = reds.getArray();

        if (level > this.level.getValue() && level <= maxLevel.getValue()) {
            switch (level) {
                case 1: {
//                    11
                    blacks.setNewArray(new BlackResource[11]);
                    blues.setNewArray(new BlueResource[11]);
                    greens.setNewArray(new GreenResource[11]);
                    reds.setNewArray(new RedResource[11]);
                }
                break;
                case 2: {
//                    17
                    blacks.setNewArray(new BlackResource[17]);
                    blues.setNewArray(new BlueResource[17]);
                    greens.setNewArray(new GreenResource[17]);
                    reds.setNewArray(new RedResource[17]);
                }
                break;
                case 3: {
//                    23
                    blacks.setNewArray(new BlackResource[23]);
                    blues.setNewArray(new BlueResource[23]);
                    greens.setNewArray(new GreenResource[23]);
                    reds.setNewArray(new RedResource[23]);
                }
                break;
                default: {
                    return false;
                }
            }
            addAll(oldBlacks, blacks.getArray());
            addAll(oldBlues, blues.getArray());
            addAll(oldGreens, greens.getArray());
            addAll(oldReds, reds.getArray());

            this.level.setValue(level);

            return true;
        } else {
            return false;
        }
    }
}
