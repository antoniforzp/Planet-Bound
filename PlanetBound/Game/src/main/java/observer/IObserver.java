package observer;

import java.util.concurrent.Callable;

/**
 * SUBSCRIBER
 */
public interface IObserver {
    void update(String property);

    void assign(String property, Callable<Void> update);
}
