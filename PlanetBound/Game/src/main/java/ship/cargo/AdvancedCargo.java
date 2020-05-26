package ship.cargo;

import resources.types.*;

import java.util.ArrayList;

public class AdvancedCargo extends Cargo {

    public AdvancedCargo() {
        this.observers = new ArrayList<>();
        this.maxLevel = 3;
        this.level = 0;

        //on the board is 1-6
        blacks = new BlackResource[5];
        blues = new BlueResource[5];
        greens = new GreenResource[5];
        reds = new RedResource[5];
        artefacts = new Artefact[5];
    }

    //ICARGO INTERFACE REST OF THE IMPLEMENTATION

    @Override
    public boolean upgrade(int level) {

        BlackResource[] oldBlacks = blacks;
        BlueResource[] oldBlues = blues;
        GreenResource[] oldGreens = greens;
        RedResource[] oldReds = reds;

        if (level > this.level && level <= maxLevel) {
            switch (level) {
                case 1: {
                    blacks = new BlackResource[11];
                    blues = new BlueResource[11];
                    greens = new GreenResource[11];
                    reds = new RedResource[11];
                }
                break;
                case 2: {
                    blacks = new BlackResource[17];
                    blues = new BlueResource[17];
                    greens = new GreenResource[17];
                    reds = new RedResource[17];
                }
                break;
                case 3: {
                    blacks = new BlackResource[23];
                    blues = new BlueResource[23];
                    greens = new GreenResource[23];
                    reds = new RedResource[23];
                }
                break;
                default: {
                    return false;
                }
            }
            addAll(oldBlacks, blacks);
            addAll(oldBlues, blues);
            addAll(oldGreens, greens);
            addAll(oldReds, reds);

            this.level = level;

            notifyChange("cargo");
            return true;
        } else {
            return false;
        }
    }
}
