package ship.cargo;

import resources.*;
import resources.types.*;

import java.util.ArrayList;

public class BasicCargo extends Cargo {

    public BasicCargo() {
        this.observers = new ArrayList<>();
        this.maxLevel = 1;
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

        if (level == 1 && this.level != level) {

            IResource[] temp = blacks;
            blacks = new BlackResource[11];
            addAll(temp, blacks);

            temp = blues;
            blues = new BlueResource[11];
            addAll(temp, blues);

            temp = greens;
            greens = new GreenResource[11];
            addAll(temp, greens);

            temp = reds;
            reds = new RedResource[11];
            addAll(temp, reds);

            this.level = level;

            notifyChange("cargo");
            return true;
        } else {
            return false;
        }
    }

}
