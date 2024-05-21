package objects.entitiesObjects;

import objects.IntersectableObjectDrawableOptions;
import utils.MyColor;
import utils.MyVec3;

public class DraughtboardDrawableOptions extends IntersectableObjectDrawableOptions {

    private final MyColor SecondaryColor;
    /**
     * Constructor.
     *
     * @param objColor      the color of the object
     * @param specularColor the specular color of the object
     * @param shininess    the shininess of the object
     * @param secondaryColor the secondary color of the object
     */
    public DraughtboardDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor) {
        super(objColor, specularColor, shininess);
        this.SecondaryColor = secondaryColor;
    }

    /**
     * Constructor.
     *
     * @param objColor      the color of the object
     * @param specularColor the specular color of the object
     * @param shininess    the shininess of the object
     * @param secondaryColor the secondary color of the object
     */
    public DraughtboardDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor,double reflectionCoeff,
                                       double transmissionCoeff, double refractionIndex) {
        super(objColor, specularColor, shininess, reflectionCoeff, transmissionCoeff, refractionIndex);
        this.SecondaryColor = secondaryColor;
    }


    /**
     * Constructor.
     *
     * @param objColor      the color of the object
     * @param specularColor the specular color of the object
     * @param shininess    the shininess of the object
     * @param secondaryColor the secondary color of the object
     */
    public DraughtboardDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor,double reflectionCoeff) {
        super(objColor, specularColor, shininess, reflectionCoeff, 0D, 0D);
        this.SecondaryColor = secondaryColor;
    }

    /**
     *
     * @param I the intersection point
     * @return the color of the intersection point
     */
    @Override
    public MyColor calculateIntersectionColor(MyVec3 I) {
        return (Math.floor(I.getX()) + Math.floor(I.getZ())) % 2.0D == 0.0D ? getColor() : SecondaryColor;
    }
}
