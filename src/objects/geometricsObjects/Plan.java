package objects.geometricsObjects;

import objects.IntersectObjectDrawOptions;
import objects.IntersectableObject;
import utils.MyVec3;

public class Plan extends IntersectableObject {
    private final MyVec3 normal;
    private final double distance;

    public Plan(IntersectObjectDrawOptions options, MyVec3 normal, double distance) {
        super(options);
        this.normal = normal;
        this.distance = distance;
    }

    @Override
    public double getIntersection(MyVec3 origin, MyVec3 direction) {
        double dotProductNormalDirection = normal.dotProduct(direction); // n.v

        if (dotProductNormalDirection != 0.0D) {
            double dotProductNormalOrigin = normal.dotProduct(origin); // n.P
            double lambdaI = (-dotProductNormalOrigin - distance) / dotProductNormalDirection; // (-n.P - d) / n.v

            if (lambdaI > 0.0001D)
                return lambdaI;
        }

        return -1.0D;
    }

    @Override
    public MyVec3 getNormal(MyVec3 I) {
        return normal;
    }
}
