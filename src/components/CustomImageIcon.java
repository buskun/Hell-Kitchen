package components;

import javax.swing.*;
import java.awt.*;

public class CustomImageIcon extends ImageIcon {
    Image originalImage = null;

    public CustomImageIcon(String fname) { super(fname); }

    public CustomImageIcon(Image cOriginalImage) {
        super(cOriginalImage);
        originalImage = cOriginalImage;
    }

    public CustomImageIcon(Image cOriginalImage, Image image) {
        super(image);
        originalImage = cOriginalImage;
    }

    public CustomImageIcon resize(int width, int height) {
        if (originalImage == null) originalImage = getImage();
        return new CustomImageIcon(originalImage, originalImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
    }

    public CustomImageIcon resize(Dimension size) {
        return resize(size.width, size.height);
    }

    public CustomImageIcon scaleToWidth(int width) {
        Dimension imageSize = getImageDimension();
        double scale = (double) width / imageSize.width;

        return resize(width, (int) Math.round(imageSize.height * scale));
    }

    public CustomImageIcon scaleToHeight(int height) {
        Dimension imageSize = getImageDimension();
        double scale = (double) height / imageSize.height;

        return resize((int) Math.round(imageSize.width * scale), height);
    }

    public CustomImageIcon scaleToFill(int width, int height) {
        Dimension imageSize = getImageDimension();
        double scaleW = (double) width / imageSize.width;
        double scaleH = (double) height / imageSize.width;
        double scale = Math.max(scaleH, scaleW);

        return resize((int) Math.round(width * scale), (int) Math.round(height * scale));

    }

    public CustomImageIcon scaleToFill(Dimension size) {
        return scaleToFill(size.width, size.height);
    }

    public CustomImageIcon scaleToFit(int width, int height) {
        Dimension imageSize = getImageDimension();
        double scaleW = (double) width / imageSize.width;
        double scaleH = (double) height / imageSize.width;
        double scale = Math.min(scaleH, scaleW);

        return resize((int) Math.round(width * scale), (int) Math.round(height * scale));

    }

    public CustomImageIcon scaleToFit(Dimension size) {
        return scaleToFit(size.width, size.height);
    }

    public Dimension getImageDimension() {
        Image image = getImage();
        return new Dimension(image.getWidth(null), image.getHeight(null));
    }
}
