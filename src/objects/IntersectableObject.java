package objects;

import utils.MyColor;
import utils.MyVec3;

/**
 * @author : Mazen
 * @version : 1.0
 */
public abstract class IntersectableObject  {

    protected IntersectableObjectDrawableOptions drawOptions;

    /**
     * Constructor.
     *
     * @param options the draw options
     */
    public IntersectableObject (IntersectableObjectDrawableOptions options) {
        this.drawOptions = options;
    }

    /**
     * Compute the intersection between the object and the ray.
     *
     * @param origin the origin (P) of the ray
     * @param direction the direction (V) of the ray
     * @return the intersection
     */
    public abstract double getIntersection(MyVec3 origin, MyVec3 direction);

    /**
     *
     * @param I the intersection
     * @return the normal of the object
     */
    public abstract MyVec3 getNormal(MyVec3 I);

    /**
     *
     * @param I the intersection
     * @return the color of the object
     */
    public abstract MyColor getTextureColor(MyVec3 I);

    /**
     *
     * @param origin the origin (P) of the ray
     * @param direction the direction (V) of the ray
     * @param lambdaI the distance from the origin of the ray to the intersection
     *                point
     * @return the intersection point
     */
    public MyVec3 getIntersectionPoint(MyVec3 origin, MyVec3 direction, double lambdaI) {
        return origin.add(direction.mul(lambdaI));
    }

    /**
     *
     * @return the draw options of the object
     */
    public IntersectableObjectDrawableOptions getDrawOptions() {
        return drawOptions;
    }


}
