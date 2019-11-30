package utility;

public class StateManager<T> {
    private T value;

    public StateManager(T initValue) {
        value = initValue;
    }

    public StateManager() {
        this(null);
    }

    public void set(T newValue) { value = newValue; }

    public T get() { return value; }
}
