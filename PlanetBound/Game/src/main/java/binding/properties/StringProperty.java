package binding.properties;

import binding.Property;

public class StringProperty extends Property {

    String value;

    public StringProperty() {
        super();
        this.value = "";
    }

    public StringProperty(String value) {
        super();
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        String old = this.value;
        this.value = value;

        callHandlers(old, this.value);
    }
}
