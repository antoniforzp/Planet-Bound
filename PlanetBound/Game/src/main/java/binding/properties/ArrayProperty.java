package binding.properties;

import binding.Property;

import java.util.Arrays;

public class ArrayProperty<T> extends Property {

    T[] array;

    public ArrayProperty(T[] array) {
        super();
        this.array = array;
    }

    public ArrayProperty() {
        super();
        this.array = null;
    }

    public T[] getArray() {
        return array;
    }

    public void setNewArray(T[] newArray) {
        T[] oldArr = Arrays.copyOf(array, array.length);
        T[] newArr = Arrays.copyOf(newArray, newArray.length);

        this.array = newArray;
        callHandlers(oldArr, newArr);
    }

    public void setValue(int i, T value) {

        T[] oldArr = Arrays.copyOf(array, array.length);
        T[] newArr = Arrays.copyOf(oldArr, array.length);
        newArr[i] = value;

        this.array = newArr;
        callHandlers(oldArr, newArr);
    }

    public T getValue(int i) {
        return array[i];
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (T val : array) {
            str.append(val).append("\t");
        }
        return str.toString();
    }
}
