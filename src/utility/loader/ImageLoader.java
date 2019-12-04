package utility.loader;

import components.CustomImageIcon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class ImageLoader extends ResourceLoader<Image> {
    public ImageLoader(Runnable onStartLoading, Runnable onLoaded) {
        super(onStartLoading, onLoaded, file -> {
            try {
                return ImageIO.read(file);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            return null;
        }, () -> {
            try {
                return ImageIO.read(new File("resources/images/base/fallback.png"));
            } catch (Exception exception) {
                System.err.println("Cannot find fallback image");
            }

            return null;
        });
    }

    public CustomImageIcon getIcon(String name) {
        return new CustomImageIcon(get(name));
    }
}
