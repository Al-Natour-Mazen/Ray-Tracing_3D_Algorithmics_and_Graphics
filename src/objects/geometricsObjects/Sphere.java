package objects.geometricsObjects;

import objects.IntersectableObject;
import objects.IntersectObjectDrawOptions;
import utils.MyVec3;

public class Sphere extends IntersectableObject {

    protected final MyVec3 center;
    protected final double radius;

    public Sphere(IntersectObjectDrawOptions options, MyVec3 center, double radius) {
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
}
