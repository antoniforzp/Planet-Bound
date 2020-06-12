package ship.cargo;

import binding.properties.ArrayProperty;
import binding.properties.IntegerProperty;
import config.Logger;
import game.singletons.Data;
import resources.IResource;
import resources.types.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Cargo implements Serializable {

    protected IntegerProperty maxLevel;
    protected IntegerProperty level;

    protected ArrayProperty<BlackResource> blacks;
    protected ArrayProperty<BlueResource> blues;
    protected ArrayProperty<GreenResource> greens;
    protected ArrayProperty<RedResource> reds;
    protected ArrayProperty<Artefact> artefacts;

    public Cargo() {
//        Data.getInstance().getBinder().addProperty(maxLevel);
//        Data.getInstance().getBinder().addProperty(level);
//        Data.getInstance().getBinder().addProperty(blacks);
//        Data.getInstance().getBinder().addProperty(blues);
//        Data.getInstance().getBinder().addProperty(greens);
//        Data.getInstance().getBinder().addProperty(reds);
//        Data.getInstance().getBinder().addProperty(artefacts);
    }

    //INNER METHODS
    private ArrayProperty<? extends IResource> toggleContainer(IResource resource) {

        ArrayProperty<? extends IResource> temp = new ArrayProperty<>();

        if (resource != null) {
            switch (resource.getType()) {
                case Black:
                    temp = blacks;
                    break;
                case Red:
                    temp = reds;
                    break;
                case Green:
                    temp = greens;
                    break;
                case Blue:
                    temp = blues;
                    break;
                case Artifact:
                    temp = artefacts;
                    break;
            }
        }
        return temp;
    }

    private boolean load(IResource resource) {

        ArrayProperty<IResource> temp = (ArrayProperty<IResource>) toggleContainer(resource);

        int i = 0;
        while (true) {
            if (i >= temp.getArray().length) {
                return false;
            } else if (temp.getValue(i) == null) {
                temp.setValue(i, resource);
                return true;
            }
            i++;
        }
    }

    private boolean remove(IResource resource) {

        ArrayProperty<IResource> temp = (ArrayProperty<IResource>) toggleContainer(resource);

        int i = temp.getArray().length - 1;
        while (true) {
            if (i < 0) {
                return false;
            } else if (temp.getValue(i) != null) {
                temp.setValue(i, null);
                return true;
            }
            i--;
        }
    }

    void addAll(IResource[] from, IResource[] to) {
        System.arraycopy(from, 0, to, 0, from.length);
    }


    public boolean loadResources(IResource[] resources) {
        boolean check = true;
        for (IResource r : resources) {
            if (!load(r)) check = false;
        }

        Logger.log("Loaded resources: " + Arrays.toString(resources));
        return check;
    }

    public boolean removeResources(IResource[] resources) {
        boolean check = true;
        for (IResource r : resources) {
            if (!remove(r)) check = false;
        }

        Logger.log("Removed resources: " + Arrays.toString(resources));
        return check;
    }


    public boolean contains(IResource[] resources) {

        ArrayList<BlackResource> blacksT = new ArrayList<>();
        ArrayList<RedResource> redsT = new ArrayList<>();
        ArrayList<GreenResource> greensT = new ArrayList<>();
        ArrayList<BlueResource> bluesT = new ArrayList<>();
        ArrayList<Artefact> artifactsT = new ArrayList<>();

        for (IResource r : resources) {
            if (r != null) {
                switch (r.getType()) {
                    case Black:
                        blacksT.add((BlackResource) r);
                        break;
                    case Red:
                        redsT.add((RedResource) r);
                        break;
                    case Green:
                        greensT.add((GreenResource) r);
                        break;
                    case Blue:
                        bluesT.add((BlueResource) r);
                        break;
                    case Artifact:
                        artifactsT.add((Artefact) r);
                        break;
                    default: {
                        return false;
                    }
                }
            }
        }
        return blacksT.size() <= getCapacity(blacks.getArray()) &&
                redsT.size() <= getCapacity(reds.getArray()) &&
                greensT.size() <= getCapacity(greens.getArray()) &&
                bluesT.size() <= getCapacity(blues.getArray()) &&
                artifactsT.size() <= getCapacity(artefacts.getArray());
    }

    public boolean checkEmpty(IResource[] resources) {
        boolean check = true;
        for (IResource r : resources) {
            if (r != null) {
                check = false;
                break;
            }
        }
        return check;
    }


    public ArrayProperty<Artefact> artefactArrayProperty() {
        return artefacts;
    }

    public ArrayProperty<BlackResource> blackResourceArrayProperty() {
        return blacks;
    }

    public ArrayProperty<BlueResource> blueResourceArrayProperty() {
        return blues;
    }

    public ArrayProperty<RedResource> redResourceArrayProperty() {
        return reds;
    }

    public ArrayProperty<GreenResource> greenResourceArrayProperty() {
        return greens;
    }


    public int getLevel() {
        return level.getValue();
    }

    public IntegerProperty maxLevelIntegerProperty() {
        return maxLevel;
    }

    public IntegerProperty levelIntegerProperty() {
        return level;
    }

    public BlackResource[] getBlacks() {
        return blacks.getArray();
    }

    public BlueResource[] getBlues() {
        return blues.getArray();
    }

    public GreenResource[] getGreens() {
        return greens.getArray();
    }

    public RedResource[] getReds() {
        return reds.getArray();
    }

    public Artefact[] getArtifacts() {
        return artefacts.getArray();
    }


    public boolean isBlacksEmpty() {
        return checkEmpty(blacks.getArray());
    }

    public boolean isBluesEmpty() {
        return checkEmpty(blues.getArray());
    }

    public boolean isGreensEmpty() {
        return checkEmpty(greens.getArray());
    }

    public boolean isRedsEmpty() {
        return checkEmpty(reds.getArray());
    }

    public int getCapacity(IResource[] resources) {
        int counter = 0;
        for (IResource r : resources) {
            if (r != null) counter++;
        }
        return counter;
    }

    public boolean upgrade(int level) {
        return false;
    }

    //TO STRING

    @Override
    public String toString() {
        return " black:\t" + getCapacity(blacks.getArray()) + "/" + (blacks.getArray().length + 1) + "\n" +
                " blue:\t" + getCapacity(blues.getArray()) + "/" + (blues.getArray().length + 1) + "\n" +
                " green:\t" + getCapacity(greens.getArray()) + "/" + (greens.getArray().length + 1) + "\n" +
                " red:\t" + getCapacity(reds.getArray()) + "/" + (reds.getArray().length + 1) + "\n" +
                " artif:\t" + getCapacity(artefacts.getArray()) + "/" + (artefacts.getArray().length + 1) + "\n";
    }
}