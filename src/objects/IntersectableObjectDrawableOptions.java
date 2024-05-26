package objects;

import utils.MyColor;
import utils.MyVec3;
import utils.Texture;

/**
 * @author : Mazen
 * @version : 1.0
 */
public class IntersectableObjectDrawableOptions {

    private final MyColor color;
    private final MyColor specularColor;
    private final double shininess;
    private double kr;
    private double kt;
    private double eta;
    private Texture texture;

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
     * Constructor.
     *
     * @param objColor          the color of the object
     * @param specularColor     the specular color of the object
     * @param shininess         the shininess of the object
     * @param reflectionCoeff   the reflection coefficient of the object
     * @param transmissionCoeff the transmission coefficient of the object
     * @param refractionIndex   the refraction index of the object
     */
    public IntersectableObjectDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, double reflectionCoeff,
                               double transmissionCoeff, double refractionIndex) {
        this.color = objColor;
        this.specularColor = specularColor;
        this.shininess = shininess;
        this.kr = reflectionCoeff;
        this.kt = transmissionCoeff;
        this.eta = refractionIndex;
    }

    /**
     * Constructor.
     *
     * @param objColor          the color of the object
     * @param specularColor     the specular color of the object
     * @param shininess         the shininess of the object
     * @param reflectionCoeff   the reflection coefficient of the object
     */
    public IntersectableObjectDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, double reflectionCoeff) {
        this.color = objColor;
        this.specularColor = specularColor;
        this.shininess = shininess;
        this.kr = reflectionCoeff;
        this.kt = 0.0D;
        this.eta = 0.0D;
    }

    /**
     * Constructor with a texture.
     *
     * @param shininess         the shininess of the object
     * @param specularColor     the specular color of the object
     * @param texture           the texture of the object
     */
    public IntersectableObjectDrawableOptions(Texture texture, MyColor specularColor, double shininess) {
        this.color = MyColor.white; // we don't need the color, we will use the texture color instead
        this.specularColor = specularColor;
        this.shininess = shininess;
        this.kr = 0.0D;
        this.kt = 0.0D;
        this.eta = 0.0D;
        this.texture = texture;
    }

    /**
     * Calculate the color of the intersection point.
     *
     * @param I the intersection point
     * @return the color of the intersection point
     */
    public MyColor calculateIntersectionColor(MyVec3 I) {
        return color;
    }

    /**
     *
     * @return the main color of the object
     */
    public MyColor getColor() {
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
        if(texture != null)
            return shininess * 1000; //we multiply by 1000 to increase the shininess of the texture
        return shininess;
    }

    /**
     *
     * @return the reflection coefficient of the object
     */
    public double getReflectionCoefficient() {
        return kr;
    }

    /**
     *
     * @return the transmission coefficient of the object
     */
    public double getTransmissionCoefficient() {
        return kt;
    }

    /**
     *
     * @return the refraction index of the object
     */
    public double getRefractionIndex() {
        return eta;
    }

    /**
     *
     * @return the texture of the object
     */
    public Texture getTexture() {
        return texture;
    }

}
