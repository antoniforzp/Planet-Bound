package ship.cargo;

import observer.IObservable;
import observer.IObserver;
import resources.IResource;
import resources.types.*;

import java.util.ArrayList;

public abstract class Cargo implements ICargo, IObservable {

    protected ArrayList<IObserver> observers;

    protected int maxLevel;
    protected int level;

    protected BlackResource[] blacks;
    protected BlueResource[] blues;
    protected GreenResource[] greens;
    protected RedResource[] reds;
    protected Artefact[] artefacts;

    //INNER METHODS

    private IResource[] toggleContainer(IResource resource) {
        IResource[] temp = new IResource[0];
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

        IResource[] temp = toggleContainer(resource);

        int i = 0;
        while (true) {
            if (i >= temp.length) {
                return false;
            } else if (temp[i] == null) {
                temp[i] = resource;
                return true;
            }
            i++;
        }
    }

    private boolean remove(IResource resource) {

        IResource[] temp = toggleContainer(resource);

        int i = temp.length - 1;
        while (true) {
            if (i < 0) {
                return false;
            } else if (temp[i] != null) {
                temp[i] = null;
                return true;
            }
            i--;
        }
    }

    void addAll(IResource[] from, IResource[] to) {
        System.arraycopy(from, 0, to, 0, from.length);
    }

    //ICARGO INTERFACE IMPLEMENTATION

    @Override
    public boolean loadResources(IResource[] resources) {
        boolean check = true;
        for (IResource r : resources) {
            if (!load(r)) check = false;
        }
        notifyChange("cargo");
        return check;
    }

    @Override
    public boolean removeResources(IResource[] resources) {
        boolean check = true;
        for (IResource r : resources) {
            if (!remove(r)) check = false;
        }
        notifyChange("cargo");
        return check;
    }


    @Override
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
        return blacksT.size() <= getCapacity(blacks) &&
                redsT.size() <= getCapacity(reds) &&
                greensT.size() <= getCapacity(greens) &&
                bluesT.size() <= getCapacity(blues) &&
                artifactsT.size() <= getCapacity(artefacts);
    }

    @Override
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


    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public BlackResource[] getBlacks() {
        return blacks;
    }

    @Override
    public BlueResource[] getBlues() {
        return blues;
    }

    @Override
    public GreenResource[] getGreens() {
        return greens;
    }

    @Override
    public RedResource[] getReds() {
        return reds;
    }

    @Override
    public Artefact[] getArtifacts() {
        return artefacts;
    }

    @Override
    public boolean isBlacksEmpty() {
        return checkEmpty(blacks);
    }

    @Override
    public boolean isBluesEmpty() {
        return checkEmpty(blues);
    }

    @Override
    public boolean isGreensEmpty() {
        return checkEmpty(greens);
    }

    @Override
    public boolean isRedsEmpty() {
        return checkEmpty(reds);
    }

    @Override
    public int getCapacity(IResource[] resources) {
        int counter = 0;
        for (IResource r : resources) {
            if (r != null) counter++;
        }
        return counter;
    }

    //TO STRING

    @Override
    public String toString() {
        return " black:\t" + getCapacity(blacks) + "/" + (blacks.length + 1) + "\n" +
                " blue:\t" + getCapacity(blues) + "/" + (blues.length + 1) + "\n" +
                " green:\t" + getCapacity(greens) + "/" + (greens.length + 1) + "\n" +
                " red:\t" + getCapacity(reds) + "/" + (reds.length + 1) + "\n" +
                " artif:\t" + getCapacity(artefacts) + "/" + (artefacts.length + 1) + "\n";
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