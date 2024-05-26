package objects.entitiesObjects;

import utils.MyColor;
import utils.MyVec3;

/**
 * @author : Mazen
 * @version : 1.0
 **/
public class WeirdDrawableOptions extends BrickDrawableOptions{

    /**
     * Constructor to create a drawable object with specified options
     * @param objColor the object color
     * @param specularColor the specular color
     * @param shininess the shininess
     * @param secondaryColor the secondary color
     * @param brickHeight the brick height
     * @param brickWidth the brick width
     */
    public WeirdDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor, double brickHeight, double brickWidth) {
        super(objColor, specularColor, shininess, secondaryColor, brickHeight, brickWidth);
    }

    /**
     * Constructor to create a drawable object with specified options
     * @param objColor the object color
     * @param specularColor the specular color
     * @param shininess the shininess
     * @param secondaryColor the secondary color
     * @param reflectionCoeff the reflection coefficient
     * @param brickHeight the brick height
     * @param brickWidth the brick width
     */
    public WeirdDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor, double reflectionCoeff, double transmissionCoeff, double refractionIndex, double brickHeight, double brickWidth) {
        super(objColor, specularColor, shininess, secondaryColor, reflectionCoeff, transmissionCoeff, refractionIndex, brickHeight, brickWidth);
    }

    /**
     * Constructor to create a drawable object with specified options
     * @param objColor the object color
     * @param specularColor the specular color
     * @param shininess the shininess
     * @param secondaryColor the secondary color
     * @param reflectionCoeff the reflection coefficient
     * @param brickHeight the brick height
     * @param brickWidth the brick width
     */
    public WeirdDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor, double reflectionCoeff, double brickHeight, double brickWidth) {
        super(objColor, specularColor, shininess, secondaryColor, reflectionCoeff, brickHeight, brickWidth);
    }

    @Override
    public MyColor calculateIntersectionColor(MyVec3 I) {
        double x = I.getX();
        double y = I.getY();

        // calculate the brick coordinates
        int brickX = (int) Math.floor(x / brickWidth);
        int brickY = (int) Math.floor(y / brickHeight);

        // Coordinates relative to the brick
        double relativeX = x - brickX * brickWidth;
        double relativeY = y - brickY * brickHeight;

        // Size of the cross
        double crossSize = Math.min(brickWidth, brickHeight) * 0.4;

        // Verify if the point is in the cross
        boolean isInCross = Math.abs(relativeX - brickWidth / 2) < crossSize / 2 || Math.abs(relativeY - brickHeight / 2) < crossSize / 2;

        // If the point is in the cross, return the primary color
        if (isInCross) {
            return getColor();
        } else {
            // Otherwise, return the secondary color
            return SecondaryColor;
        }
    }
}
