package utility;

import java.io.File;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ResourceLoader<T> {
    private final HashMap<String, String> waitList = new HashMap<>();
    private final HashMap<String, T> loadedList = new HashMap<>();
    private boolean loading = false;
    private T fallback;
    private Function<File, T> fileReader;
    private Runnable onStartLoadingRes;
    private Runnable onLoadedRes;

    public ResourceLoader(Runnable onStartLoading, Runnable onLoaded, Function<File, T> fileLoader, T fallbackValue) {
        fallback = fallbackValue;
        fileReader = fileLoader;
        onStartLoadingRes = onStartLoading;
        onLoadedRes = onLoaded;
    }

    public ResourceLoader(Runnable onStartLoading, Runnable onLoaded, Function<File, T> fileLoader, Supplier<T> fallbackValue) {
        fallback = fallbackValue.get();
        fileReader = fileLoader;
        onStartLoadingRes = onStartLoading;
        onLoadedRes = onLoaded;
    }

    public void add(String name, String path) {
        waitList.put(name, path);
        loadedList.put(name, null);
    }

    synchronized protected void loader(String name) {
        T loadedValue = null;
        try {
            loadedValue = fileReader.apply(new File(waitList.get(name)));

            loadedList.put(name, loadedValue);

            onLoad(name, loadedValue);
        } catch (Exception exception) {
            System.err.println(exception.toString());
            System.err.printf("Cannot load: %s (%s)\n", name, waitList.get(name));
        }

        if(loadedValue == null) {
            loadedList.remove(name);

            onLoadFailed(name);
        }
    }

    private void onLoad(String name, T value) { }

    private void onLoadFailed(String name) { }

    public void load() { load(false); }

    public void load(boolean synchronous) {
        loading = true;
        new Thread(onStartLoadingRes).start();

        if (synchronous) {
            for (String name : loadedList.keySet()) {
                if (loadedList.get(name) == null) {
                    Thread thread = new Thread(() -> loader(name));
                    thread.setPriority(Thread.MAX_PRIORITY);
                    thread.start();
                }
            }
        } else {
            Thread thread = new Thread(() -> {
                for (String name : loadedList.keySet()) {
                    if (loadedList.get(name) == null) {
                        loader(name);
                    }
                }
            });
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();
        }

        while (true) {
            boolean allLoaded = true;

            for (T img : loadedList.values()) {
                if (img == null) {
                    allLoaded = false;
                    break;
                }
            }

            if (allLoaded) break;
            Thread.currentThread().interrupt();
        }

        new Thread(onLoadedRes).start();
        loading = false;
    }

    public boolean isLoading() { return loading; }

    public T get(String name) {
        T image = loadedList.get(name);

        if (image == null) return fallback;
        return image;
    }

    public T getFallback() { return fallback; }
}
