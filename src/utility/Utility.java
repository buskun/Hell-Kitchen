package utility;

import java.util.function.Supplier;

public class Utility {
    private static class IntervalRunnable implements Runnable {
        private boolean running = true;
        private Supplier<Boolean> callback;
        private int interval;

        IntervalRunnable(Supplier<Boolean> intervalCallback, int intervalTime) {
            callback = intervalCallback;
            interval = intervalTime;
        }

        @Override
        public void run() {
            while (running) {
                running = callback.get();

                try {
                    Thread.sleep(interval);
                } catch (Exception ignored) { }
            }
        }

        void stop() { running = false; }
    }

    public static Runnable setInterval(Supplier<Boolean> callback, int interval) {
        IntervalRunnable runnable = new IntervalRunnable(callback, interval);

        new Thread(runnable).start();

        return runnable::stop;
    }

    public static void setTimeout(Runnable callback, int time) {
        new Thread(() -> {
            try { Thread.sleep(time); } catch (Exception ignored) { }

            callback.run();
        }).start();
    }

    public static <T> StateManager<T> useState(T initValue) {
        return new StateManager<>(initValue);
    }

    public static <T> StateManager<T> useState(Class<T> valueType) {
        return new StateManager<>();
    }
}
