package objects.entitiesObjects;

import objects.IntersectableObjectDrawableOptions;
import utils.MyColor;
import utils.MyVec3;

/**
 * @author : Mazen
 * @version : 1.0
 **/
public class StripedDrawableOptions extends IntersectableObjectDrawableOptions {

    private final MyColor SecondaryColor;

    /**
     * Constructor to create a striped drawable object with specified options, color, specular color, shininess, and secondary color.
     *
     * @param objColor       the object color
     * @param specularColor  the specular color
     * @param shininess      the shininess
     * @param secondaryColor the secondary color
     */
    public StripedDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor) {
        super(objColor, specularColor, shininess);
        this.SecondaryColor = secondaryColor;
    }

    /**
     * Constructor to create a striped drawable object with specified options, color, specular color, shininess, secondary color, reflection coefficient, transmission coefficient, and refraction index.
     *
     * @param objColor           the object color
     * @param specularColor      the specular color
     * @param shininess          the shininess
     * @param secondaryColor     the secondary color
     * @param reflectionCoeff    the reflection coefficient
     * @param transmissionCoeff  the transmission coefficient
     * @param refractionIndex    the refraction index
     */
    public StripedDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor, double reflectionCoeff,
                                  double transmissionCoeff, double refractionIndex) {
        super(objColor, specularColor, shininess, reflectionCoeff, transmissionCoeff, refractionIndex);
        this.SecondaryColor = secondaryColor;
    }

    /**
     * Constructor to create a striped drawable object with specified options, color, specular color, shininess, secondary color, reflection coefficient, and transmission coefficient.
     *
     * @param objColor          the object color
     * @param specularColor     the specular color
     * @param shininess         the shininess
     * @param secondaryColor    the secondary color
     * @param reflectionCoeff   the reflection coefficient
     */
    public StripedDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor, double reflectionCoeff) {
        super(objColor, specularColor, shininess, reflectionCoeff, 0D, 0D);
        this.SecondaryColor = secondaryColor;
    }

    @Override
    public MyColor calculateIntersectionColor(MyVec3 I) {
        return Math.floor(I.getX()) % 2.0D == 0.0D ? getColor() : SecondaryColor;
    }
}
