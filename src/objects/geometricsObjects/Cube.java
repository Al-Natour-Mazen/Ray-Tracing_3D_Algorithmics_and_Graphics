package objects.geometricsObjects;

import objects.IntersectableObject;
import objects.IntersectableObjectDrawableOptions;
import utils.MyVec3;

public class Cube extends IntersectableObject {

    protected final MyVec3 center;
    protected final double sideLength;

    public Cube(IntersectableObjectDrawableOptions options, MyVec3 center, double sideLength) {
        super(options);
        this.center = center;
        this.sideLength = sideLength;
    }

    @Override
    public double getIntersection(MyVec3 origin, MyVec3 direction) {
        // la logique pour calculer l'intersection entre le rayon et le cube
        return -1.0D;
    }

    @Override
    public MyVec3 getNormal(MyVec3 I) {
        // la logique pour calculer la normale au point d'intersection I
        return new MyVec3(0, 0, 0);
    }
}
