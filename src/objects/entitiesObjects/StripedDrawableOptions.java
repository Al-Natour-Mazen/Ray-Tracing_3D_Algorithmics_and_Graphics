package objects.entitiesObjects;

import objects.IntersectableObjectDrawableOptions;
import utils.MyColor;
import utils.MyVec3;

public class StripedDrawableOptions extends IntersectableObjectDrawableOptions {

    private final MyColor SecondaryColor;

    public StripedDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor) {
        super(objColor, specularColor, shininess);
        this.SecondaryColor = secondaryColor;
    }

    public StripedDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor, double reflectionCoeff,
                                  double transmissionCoeff, double refractionIndex) {
        super(objColor, specularColor, shininess, reflectionCoeff, transmissionCoeff, refractionIndex);
        this.SecondaryColor = secondaryColor;
    }

    public StripedDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor, double reflectionCoeff) {
        super(objColor, specularColor, shininess, reflectionCoeff, 0D, 0D);
        this.SecondaryColor = secondaryColor;
    }

    @Override
    public MyColor calculateIntersectionColor(MyVec3 I) {
        return Math.floor(I.getX()) % 2.0D == 0.0D ? getColor() : SecondaryColor;
    }
}
