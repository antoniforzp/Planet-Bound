package app;

import app.IController;

import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {
    static Map<Class<?>, IController> controllers = new HashMap<>();

    static public void addController(IController c) {
        controllers.put(c.getClass(), c);
    }

    static public IController getController(Class<?> classifier) {
        return controllers.getOrDefault(classifier, null);
    }
}
