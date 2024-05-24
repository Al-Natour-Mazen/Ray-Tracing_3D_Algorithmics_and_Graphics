package objects.geometricsObjects;

import objects.IntersectableObjectDrawableOptions;
import objects.IntersectableObject;
import utils.MyColor;
import utils.MyVec3;

public class Plan extends IntersectableObject {
    private final MyVec3 normal;
    private final double distance;

    public Plan(IntersectableObjectDrawableOptions options, MyVec3 normal, double distance) {
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

    @Override
    public MyColor getTextureColor(MyVec3 I) {
        // Choose an arbitrary vector not parallel to the normal
        MyVec3 arbitraryVector = (Math.abs(normal.getX()) > Math.abs(normal.getY()))
                ? new MyVec3(normal.getZ(), 0, -normal.getX())
                : new MyVec3(0, -normal.getZ(), normal.getY());

        // Create uAxis and vAxis as orthogonal vectors on the plane
        MyVec3 uAxis = normal.crossProduct(arbitraryVector);
        uAxis.normalize();
        MyVec3 vAxis = normal.crossProduct(uAxis);
        vAxis.normalize();
        // Project the intersection point I onto the plane's local coordinate system
        MyVec3 pointOnPlane = I.subtract(normal.scale(normal.dotProduct(I) + distance));
        double u = pointOnPlane.dotProduct(uAxis);
        double v = pointOnPlane.dotProduct(vAxis);

        // Normalize the UV coordinates
        double uNormalized = u - Math.floor(u);
        double vNormalized = v - Math.floor(v);

        // Get the color from the texture using the normalized UV coordinates
        return drawOptions.getTexture().getColor(uNormalized, vNormalized);
    }
}
