package utils;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents a texture.
 * A texture is an image that can be applied to an object to give it a more realistic appearance.
 * @author : Mazen
 * @version : 1.0
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
        // ensure u and v are between 0 and 1
        u = Math.max(0, Math.min(1, u));
        v = Math.max(0, Math.min(1, v));

        // Calculate the pixel coordinates
        int x = (int) (u * (image.getWidth() - 1));
        int y = (int) ((1 - v) * (image.getHeight() - 1));

        // Ensure the pixel coordinates are within the image bounds
        x = Math.max(0, Math.min(x, image.getWidth() - 1));
        y = Math.max(0, Math.min(y, image.getHeight() - 1));

        // Use Color to get the RGB values
        Color color = new Color(image.getRGB(x, y));

        // Convert the RGB values to the range [0, 1]
        float red = color.getRed() / 255.0f;
        float green = color.getGreen() / 255.0f;
        float blue = color.getBlue() / 255.0f;

        // Return the color
        return new MyColor(red, green, blue);
    }
}

