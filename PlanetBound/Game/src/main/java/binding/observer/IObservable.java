package binding.observer;

public interface IObservable {

    void addObserver(IObserver observer);

    void removeObserver(IObserver observer);

    void notifyChange(String property) throws Exception;
}