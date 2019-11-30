package utility;

import base.Scene;
import components.CustomImageIcon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageLoader {
    private Scene current;
    private final HashMap<String, String> waitList = new HashMap<>();
    private final HashMap<String, Image> loadedList = new HashMap<>();
    private final HashMap<String, CustomImageIcon> loadedIconList = new HashMap<>();
    private boolean loading = false;
    private Image fallbackImage;
    private CustomImageIcon fallbackImageIcon;

    public ImageLoader(Scene currentScene) {
        current = currentScene;
        try {
            fallbackImage = ImageIO.read(new File("resources/images/base/fallback.png"));
            fallbackImageIcon = new CustomImageIcon(fallbackImage);
        } catch (Exception exception) {
            System.err.println("Cannot find fallback Image.");
        }
    }

    public ImageLoader addImage(String name, String imagePath) {
        waitList.put(name, imagePath);
        loadedList.put(name, null);
        loadedIconList.put(name, null);
        return this;
    }

    synchronized private void loader(String name) {
        try {
            Image loadedImage = ImageIO.read(new File(waitList.get(name)));

            loadedList.put(name, loadedImage);
            loadedIconList.put(name, new CustomImageIcon(loadedImage));
        } catch (Exception exception) {
            System.err.println(exception.toString());
            System.err.printf("Cannot load image: %s (%s)\n", name, waitList.get(name));

            loadedList.remove(name);
            loadedIconList.remove(name);
        }
    }

    public void load() { load(false); }

    public void load(boolean synchronous) {
        loading = true;
        new Thread(() -> current.onStartLoadingResource()).start();

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

            for (Image img : loadedList.values()) {
                if (img == null) {
                    allLoaded = false;
                    break;
                }
            }

            if (allLoaded) break;
            Thread.currentThread().interrupt();
        }

        new Thread(() -> current.onResourceLoaded()).start();
        loading = false;
    }

    public boolean isLoading() { return loading; }

    public Image get(String name) {
        Image image = loadedList.get(name);

        if (image == null) return fallbackImage;
        return image;
    }

    public CustomImageIcon getIcon(String name) {
        CustomImageIcon icon = loadedIconList.get(name);

        if (icon == null) return fallbackImageIcon;
        return icon;
    }
}
