package utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

public class StateManager<T> {
    private ArrayList<Function<T, Boolean>> listenerList = new ArrayList<>();
    private T value;

    public StateManager(T initValue) {
        value = initValue;
    }

    public StateManager() {
        this(null);
    }

    public void set(T newValue) {
        value = newValue;

        new Thread(() -> {
            for (Function<T, Boolean> listener : listenerList) {
                if (listener.apply(value)) break;
            }
        }).start();
    }

    public T get() { return value; }

    public void addListener(Function<T, Boolean> listener) { listenerList.add(listener); }

    public void removeListener(Function<T, Boolean> listener) { listenerList.removeAll(Collections.singleton(listener)); }

    public void clearListener() { listenerList = new ArrayList<>(); }
}
