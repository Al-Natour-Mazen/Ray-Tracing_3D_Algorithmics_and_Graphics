package objects;

import utils.MyVec3;

public abstract class IntersectableObject  {

    protected IntersectObjectDrawOptions drawOptions;

    /**
     * Constructor.
     *
     * @param options the draw options
     */
    public IntersectableObject (IntersectObjectDrawOptions options) {
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
    public IntersectObjectDrawOptions getDrawOptions() {
        return drawOptions;
    }


}
