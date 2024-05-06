package objects;

import utils.MyColor;
import utils.MyVec3;

public class IntersectableObjectDrawableOptions {

    private final MyColor color;
    private final MyColor specularColor;
    private final double shininess;

    /**
     * Constructor.
     *
     * @param objColor         the color of the object
     * @param specularColor the specular color of the object
     */
    public IntersectableObjectDrawableOptions(MyColor objColor, MyColor specularColor, double shininess) {
        this.color = objColor;
        this.specularColor = specularColor;
        this.shininess = shininess;
    }

    /**
     *
     * @param I the intersection point
     * @return the color of the intersection point
     */
    public MyColor getColor(MyVec3 I) {
        return color;
    }

    /**
     *
     * @return the specular color of the object
     */
    public MyColor getSpecularColor() {
        return specularColor;
    }

    /**
     *
     * @return the shininess of the object
     */
    public double getShininess() {
        return shininess;
    }
}
