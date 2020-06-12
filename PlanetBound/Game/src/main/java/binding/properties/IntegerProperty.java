package binding.properties;

import binding.Property;

public class IntegerProperty extends Property {

    Integer value;

    public IntegerProperty() {
        super();
        this.value = null;
    }

    public IntegerProperty(Integer value) {
        super();
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        Integer old = this.value;
        this.value = value;

        callHandlers(old, this.value);
    }
}