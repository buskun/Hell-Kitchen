package utility.loader;

import java.io.File;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ResourceLoader<T> {
    private HashMap<String, String> waitList = new HashMap<>();
    private HashMap<String, T> loadedList = new HashMap<>();
    private HashMap<String, T> loadedListQueue = new HashMap<>();
    private boolean loading = false;
    private int timeout = 10000;
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

    public void setTimeout(int timeout) { this.timeout = timeout; }

    public void add(String name, String path) {
        waitList.put(name, path);
        loadedList.put(name, null);
        loadedListQueue.put(name, null);
    }

    synchronized protected void loader(String name) {
        T loadedValue = null;
        try {
            loadedValue = fileReader.apply(new File(waitList.get(name)));

            loadedListQueue.put(name, loadedValue);

            onLoad(name, loadedValue);
        } catch (Exception exception) {
            System.err.println(exception.toString());
            System.err.printf("Cannot load: %s (%s)\n", name, waitList.get(name));
        }

        if (loadedValue == null) {
            onLoadFailed(name);
        }
    }

    private void onLoad(String name, T value) { }

    private void onLoadFailed(String name) { }

    public void load() { load(false); }

    public void load(boolean synchronous) {
        long startTime = System.currentTimeMillis();
        loading = true;
        onStartLoadingRes.run();

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

            for (T img : loadedListQueue.values()) {
                if (img == null) {
                    allLoaded = false;
                    break;
                }
            }

            if (timeout != 0 && System.currentTimeMillis() - startTime > timeout) break;
            if (allLoaded) break;
            try { wait(); } catch (Exception ignored) {}
        }

        loadedList = loadedListQueue;
        loadedListQueue = new HashMap<>();
        loadedList.forEach((key, value) -> loadedListQueue.put(key, null));

        onLoadedRes.run();
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
