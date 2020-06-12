package binding.properties;

import binding.Property;

import java.util.ArrayList;
import java.util.List;

public class ListProperty<T> extends Property {

    List<T> collection;

    public ListProperty() {
        super();
        this.collection = new ArrayList<>();
    }

    public ListProperty(List<T> value) {
        super();
        this.collection = value;
    }

    public T getElement(int i) {
        return collection.get(i);
    }

    public List<T> getList() {
        return collection;
    }

    public void setNewList(List<T> newList) {
        this.collection = newList;
    }

    public void setElement(int i, T value) {

        List<T> oldVal = new ArrayList<>(this.collection);
        List<T> newVal = new ArrayList<>(oldVal);
        newVal.set(i, value);

        this.collection = newVal;
        callHandlers(oldVal, newVal);
    }

    public void addElement(T value) {

        List<T> oldVal = new ArrayList<>(this.collection);
        List<T> newVal = new ArrayList<>(oldVal);
        newVal.add(value);

        this.collection = newVal;
        callHandlers(oldVal, newVal);
    }

    public void removeElement(T value) {

        List<T> oldVal = new ArrayList<>(this.collection);
        List<T> newVal = new ArrayList<>(oldVal);
        newVal.remove(value);

        this.collection = newVal;
        callHandlers(oldVal, newVal);
    }

    public T removeElement(int i) {

        List<T> oldVal = new ArrayList<>(this.collection);
        List<T> newVal = new ArrayList<>(oldVal);
        T removed = newVal.remove(i);

        this.collection = newVal;
        callHandlers(oldVal, newVal);

        return removed;
    }

    public void addAll(List<T> collection) {

        List<T> oldVal = new ArrayList<>(this.collection);
        List<T> newVal = new ArrayList<>(oldVal);
        newVal.addAll(collection);

        this.collection = newVal;
        callHandlers(oldVal, newVal);
    }
}
