package objects.entitiesObjects;

import objects.IntersectableObjectDrawableOptions;
import utils.MyColor;
import utils.MyVec3;

public class BrickDrawableOptions extends IntersectableObjectDrawableOptions {

    private final MyColor SecondaryColor;
    private final double brickHeight;
    private final double brickWidth;

    public BrickDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor, double brickHeight, double brickWidth) {
        super(objColor, specularColor, shininess);
        this.SecondaryColor = secondaryColor;
        this.brickHeight = brickHeight;
        this.brickWidth = brickWidth;
    }

    public BrickDrawableOptions(MyColor objColor, MyColor specularColor, double shininess, MyColor secondaryColor, double reflectionCoeff,
                                double transmissionCoeff, double refractionIndex, double brickHeight, double brickWidth) {
        super(objColor, specularColor, shininess, reflectionCoeff, transmissionCoeff, refractionIndex);
        this.SecondaryColor = secondaryColor;
        this.brickHeight = brickHeight;
        this.brickWidth = brickWidth;
    }

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
