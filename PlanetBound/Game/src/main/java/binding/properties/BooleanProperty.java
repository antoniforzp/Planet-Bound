package binding.properties;

import binding.Property;

public class BooleanProperty extends Property {

    Boolean value;

    public BooleanProperty() {
        super();
        this.value = null;
    }

    public BooleanProperty(Boolean value) {
        super();
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        Boolean old = this.value;
        this.value = value;

        callHandlers(old, this.value);
    }
}
