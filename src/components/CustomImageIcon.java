package components;

import javax.swing.*;
import java.awt.*;

public class CustomImageIcon extends ImageIcon {
    Image originalImage;

    public CustomImageIcon(String fname) {
        super(fname);
        originalImage = getImage();
    }

    public CustomImageIcon(Image cOriginalImage) {
        super(cOriginalImage);
        originalImage = cOriginalImage;
    }

    public CustomImageIcon(Image cOriginalImage, Image image) {
        super(image);
        originalImage = cOriginalImage;
    }

    public CustomImageIcon resize(int width, int height) {
        return new CustomImageIcon(originalImage, originalImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
    }

    public CustomImageIcon resize(Dimension size) {
        return resize(size.width, size.height);
    }

    public CustomImageIcon scaleToWidth(int width) {
        Dimension imageSize = getImageDimension();
        double scale = (double) width / imageSize.width;

        return resize(width, (int) (imageSize.height * scale));
    }

    public CustomImageIcon scaleToHeight(int height) {
        Dimension imageSize = getImageDimension();
        double scale = (double) height / imageSize.height;

        return resize((int) (imageSize.width * scale), height);
    }

    public CustomImageIcon scaleToFill(int width, int height) {
        int imgWidth = originalImage.getWidth(null);
        int imgHeight = originalImage.getHeight(null);
        double scaleW = (double) width / imgWidth;
        double scaleH = (double) height / imgHeight;
        double scale = Math.max(scaleH, scaleW);

        return resize((int) (imgWidth * scale), (int) (imgHeight * scale));

    }

    public CustomImageIcon scaleToFill(Dimension size) {
        return scaleToFill(size.width, size.height);
    }

    public CustomImageIcon scaleToFit(int width, int height) {
        int imgWidth = originalImage.getWidth(null);
        int imgHeight = originalImage.getHeight(null);
        double scaleW = (double) width / imgWidth;
        double scaleH = (double) height / imgHeight;
        double scale = Math.min(scaleH, scaleW);

        return resize((int) (imgWidth * scale), (int) (imgHeight * scale));

    }

    public CustomImageIcon scaleToFit(Dimension size) {
        return scaleToFit(size.width, size.height);
    }

    public Dimension getImageDimension() {
        Image image = getImage();
        return new Dimension(image.getWidth(null), image.getHeight(null));
    }
}
