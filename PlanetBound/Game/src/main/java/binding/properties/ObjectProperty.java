package binding.properties;

import binding.Property;

import java.util.ArrayList;

public class ObjectProperty<T> extends Property {

    T value;

    public ObjectProperty() {
        super();
        this.value = null;
    }

    public ObjectProperty(T value) {
        super();
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {

        ArrayList<T> currArr = new ArrayList<>();
        currArr.add(this.value);

        ArrayList<T> old = new ArrayList<>(currArr);

        this.value = value;
        callHandlers(old.get(0), this.value);
    }
}
