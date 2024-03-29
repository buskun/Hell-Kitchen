package utility;

import base.Scene;
import components.CustomImageIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.function.Supplier;

public class Utility {
    private static HashMap<String, Font> customFontList = new HashMap<>();

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

    public static Runnable setInterval(Scene scene, Supplier<Boolean> callback, int interval) {
        return setInterval(() -> {
            boolean continued = callback.get();
            scene.repaint();
            return continued;
        }, interval);
    }

    public static Runnable setTimeout(Runnable callback, int time) {
        StateManager<Boolean> interrupt = useState(false);

        Thread timeoutThread = new Thread(() -> {
            try { Thread.sleep(time); } catch (Exception ignored) { }

            if (interrupt.get()) return;

            callback.run();
        });

        timeoutThread.start();

        return () -> {
            interrupt.set(true);
            timeoutThread.interrupt();
        };
    }

    public static Runnable setTimeout(Scene scene, Runnable callback, int time) {
        return setTimeout(() -> {
            callback.run();
            scene.repaint();
        }, time);
    }

    public static <T> StateManager<T> useState(T initValue) {
        return new StateManager<>(initValue);
    }

    public static <T> StateManager<T> useState(Class<T> valueType) {
        return new StateManager<>();
    }

    public static void addCustomFont(String fontName, String fontPath) {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(customFont);

            customFontList.put(fontName, customFont);
        } catch (IOException e) {
            System.err.println("Cannot find font in " + fontPath);
        } catch (FontFormatException e) {
            System.err.println("Font in " + fontPath + " is invalid");
        }
    }

    public static Font getFont(String name) { return customFontList.get(name); }

    public static void setDefaultFont(Font font) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();

        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);

            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, font);
        }
    }

    public static void setHoverIcon(JComponent component, CustomImageIcon normalIcon, CustomImageIcon hoverIcon) {
        component.addMouseListener(new MouseAdapter() {
            private void setIcon(CustomImageIcon nIcon) {
                try {
                    component.getClass().getMethod("setIcon", Icon.class)
                            .invoke(component, nIcon.resize(component.getSize()));
                } catch (Exception ignored) {}
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                synchronized (component) {
                    setIcon(hoverIcon);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                synchronized (component) {
                    setIcon(normalIcon);
                }
            }
        });
    }

    public static void setActiveIcon(JComponent component, CustomImageIcon normalIcon, CustomImageIcon activeIcon) {
        component.addMouseListener(new MouseAdapter() {
            private void setIcon(CustomImageIcon nIcon) {
                try {
                    component.getClass().getMethod("setIcon", Icon.class)
                            .invoke(component, nIcon.resize(component.getSize()));
                } catch (Exception ignored) {}
            }

            @Override
            public void mousePressed(MouseEvent e) {
                synchronized (component) {
                    setIcon(activeIcon);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                synchronized (component) {
                    setIcon(normalIcon);
                }
            }
        });
    }
}
