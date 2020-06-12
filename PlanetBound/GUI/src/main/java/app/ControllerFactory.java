package app;

import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {
    static Map<Class<?>, Controller> controllers = new HashMap<>();

    static public void addController(Controller c) {
        controllers.put(c.getClass(), c);
    }

    static public Controller getController(Class<?> classifier) {
        return controllers.getOrDefault(classifier, null);
    }
}
