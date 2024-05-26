package objects.entitiesObjects;

import objects.IntersectableObjectDrawableOptions;
import utils.MyColor;
import utils.MyVec3;

/**
 * @author : Mazen
 * @version : 1.0
 **/
public class BrickDrawableOptions extends IntersectableObjectDrawableOptions {

    protected final MyColor SecondaryColor;
    protected final double brickHeight;
    protected final double brickWidth;

    /**
     * Constructor.
     *
     * @param objColor      the color of the object
     * @param specularColor the specular color of the object
     * @param shininess    the shininess of the object
     * @param secondaryColor the secondary color of the object
     * @param brickHeight the height of the brick
     * @param brickWidth the width of the brick
     */
    public BrickDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor, double brickHeight, double brickWidth) {
        super(objColor, specularColor, shininess);
        this.SecondaryColor = secondaryColor;
        this.brickHeight = brickHeight;
        this.brickWidth = brickWidth;
    }

    /**
     * Constructor.
     *
     * @param objColor      the color of the object
     * @param specularColor the specular color of the object
     * @param shininess    the shininess of the object
     * @param secondaryColor the secondary color of the object
     * @param reflectionCoeff the reflection coefficient
     * @param transmissionCoeff the transmission coefficient
     * @param refractionIndex the refraction index
     * @param brickHeight the height of the brick
     * @param brickWidth the width of the brick
     */
    public BrickDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor, double reflectionCoeff,
                                double transmissionCoeff, double refractionIndex, double brickHeight, double brickWidth) {
        super(objColor, specularColor, shininess, reflectionCoeff, transmissionCoeff, refractionIndex);
        this.SecondaryColor = secondaryColor;
        this.brickHeight = brickHeight;
        this.brickWidth = brickWidth;
    }

    /**
     * Constructor.
     *
     * @param objColor      the color of the object
     * @param specularColor the specular color of the object
     * @param shininess    the shininess of the object
     * @param secondaryColor the secondary color of the object
     * @param reflectionCoeff the reflection coefficient
     * @param brickHeight the height of the brick
     * @param brickWidth the width of the brick
     */
    public BrickDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor, double reflectionCoeff, double brickHeight, double brickWidth) {
        super(objColor, specularColor, shininess, reflectionCoeff, 0D, 0D);
        this.SecondaryColor = secondaryColor;
        this.brickHeight = brickHeight;
        this.brickWidth = brickWidth;
    }

    @Override
    public MyColor calculateIntersectionColor(MyVec3 I) {
        double x = I.getX();
        double y = I.getY();
        int brickX = (int) Math.floor(x / brickWidth);
        int brickY = (int) Math.floor(y / brickHeight);
        boolean isEvenRow = brickY % 2 == 0;
        boolean isEvenColumn = brickX % 2 == 0;
        return (isEvenRow == isEvenColumn) ? getColor() : SecondaryColor;
    }
}
