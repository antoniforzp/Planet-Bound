package app;

import observer.IObserver;
import java.util.Map;
import java.util.concurrent.Callable;

public abstract class Controller implements IObserver, IController {

    protected Map<String, Callable<Void>> updates;

    @Override
    public void update(String property) {
        for (Map.Entry<String, Callable<Void>> entry : updates.entrySet()) {
            if (entry.getKey().equals(property)) {
                try {
                    entry.getValue().call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    public void assign(String property, Callable<Void> update) {
        updates.put(property, update);
    }

}
