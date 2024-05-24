package utils;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents a texture.
 * A texture is an image that can be applied to an object to give it a more realistic appearance.
 * Author: Mazen
 * Version: 1.0
 */
public class Texture {

    private final BufferedImage image;

    /**
     * Constructor.
     *
     * @param filePath the path to the image file
     */
    public Texture(String filePath) {
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            throw new IllegalArgumentException("The image file ("+ filePath +") could not be loaded.");
        }
    }

    /**
     * Get the color of the texture at the given coordinates.
     *
     * @param u the u coordinate
     * @param v the v coordinate
     * @return the color
     */
    public MyColor getColor(double u, double v) {
        u = Math.max(0, Math.min(1, u));
        v = Math.max(0, Math.min(1, v));

        int x = (int) (u * (image.getWidth() - 1));
        int y = (int) ((1 - v) * (image.getHeight() - 1));

        x = Math.max(0, Math.min(x, image.getWidth() - 1));
        y = Math.max(0, Math.min(y, image.getHeight() - 1));

        // Use Color to get the RGB values
        Color color = new Color(image.getRGB(x, y));

        int red = color.getRed() * 255;
        int green = color.getGreen() * 255;
        int blue = color.getBlue() * 255;

        return new MyColor(red, green, blue);
    }
}

