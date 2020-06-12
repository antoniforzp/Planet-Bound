package binding;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Property implements Serializable {

    private transient ArrayList<EventHandler> handlers = new ArrayList<>();

    public void addListener(EventHandler handler) {
        if (this.handlers == null) {
            this.handlers = new ArrayList<>();
        }
        this.handlers.add(handler);
    }

    public void callHandlers(Object oldVal, Object newVal) {
        if (this.handlers != null) {
            for (EventHandler handler : handlers) {
                if (handler != null) {
                    handler.update(oldVal, newVal);
                }
            }
        }
    }
}
