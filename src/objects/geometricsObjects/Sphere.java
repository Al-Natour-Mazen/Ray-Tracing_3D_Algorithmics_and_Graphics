package objects.geometricsObjects;

import objects.IntersectableObject;
import objects.IntersectableObjectDrawableOptions;
import utils.MyColor;
import utils.MyVec3;

/**
 * @author : Mazen
 * @version : 1.0
 **/
public class Sphere extends IntersectableObject {

    protected final MyVec3 center;
    protected final double radius;

    public Sphere(IntersectableObjectDrawableOptions options, MyVec3 center, double radius) {
        super(options);
        this.center = center;
        this.radius = radius;
    }

    @Override
    public double getIntersection(MyVec3 origin, MyVec3 direction) {
        MyVec3 CP = origin.sub(center); // CP

        double a = direction.dotProduct(direction); // ||v||^2
        double b = direction.mul(2.0D).dotProduct(CP); // 2v.CP
        double c = CP.dotProduct(CP) - radius * radius; // ||CP||^2 - r^2

        double delta = b * b - 4.0D * a * c; // b^2 - 4ac

        return solveQuadraticEquation(a, b, delta);
    }

    /**
     * Solve the quadratic equation.
     *
     * @param a     the a coefficient
     * @param b     the b coefficient
     * @param delta the delta
     * @return the solution
     */
    private double solveQuadraticEquation(double a, double b, double delta) {
        if (delta == 0.0D) {
            double lambda = -b / (2.0D * a); // -b / (2a)

            if (lambda > 0.0001D)
                return lambda;
        } else if (delta > 0.0D) {
            double lambda1 = (-b - Math.sqrt(delta)) / (2.0D * a); // (-b - sqrt(delta)) / (2a)
            double lambda2 = (-b + Math.sqrt(delta)) / (2.0D * a); // (-b + sqrt(delta)) / (2a)

            if (lambda1 < 0.0001D && 0.001D < lambda2) {
                return lambda2;
            } else if (0.0001D < lambda1 && lambda1 < lambda2) {
                return lambda1;
            }
        }

        return -1.0D;
    }

    @Override
    public MyVec3 getNormal(MyVec3 I) {
        MyVec3 CI = I.sub(center); // CI
        CI.normalize();

        return CI;
    }

    @Override
    public MyColor getTextureColor(MyVec3 I) {
        // Convert intersection point to local coordinates
        MyVec3 localPoint = I.sub(center);
        localPoint.normalize();

        // Calculate spherical coordinates
        double theta = Math.acos(localPoint.getY()); // angle from Y-axis
        double phi = Math.atan2(localPoint.getZ(), localPoint.getX()); // angle from X-axis in the XZ-plane

        // Convert spherical coordinates to texture coordinates (u, v)
        double u = (phi + Math.PI) / (2 * Math.PI);
        double v = theta / Math.PI;

        // Debugging output
        // System.out.printf("Intersection point: %s, Local point: %s, Spherical coords: (theta: %.2f, phi: %.2f), UV: (%.2f, %.2f)%n", I, localPoint, theta, phi, u, v);

        // Get color from texture
        return drawOptions.getTexture().getColor(u, v);
    }
}
