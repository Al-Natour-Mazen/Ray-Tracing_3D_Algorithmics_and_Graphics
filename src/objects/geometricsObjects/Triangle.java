package objects.geometricsObjects;

import objects.Axis;
import objects.IntersectableObject;
import objects.IntersectableObjectDrawableOptions;
import utils.MyVec3;

public class Triangle extends IntersectableObject {
    private MyVec3 v0, v1, v2; // Vertices of the triangle

    // Constructor to create a triangle with specified options, center, length, angle, and axis
    public Triangle(IntersectableObjectDrawableOptions options, MyVec3 center, double length, double angle, Axis axis) {
        super(options);

        // Create the points of the triangle around the origin
        MyVec3 p0 = new MyVec3(-length / 2, -Math.sqrt(3) / 6 * length, 0);
        MyVec3 p1 = new MyVec3(length / 2, -Math.sqrt(3) / 6 * length, 0);
        MyVec3 p2 = new MyVec3(0, Math.sqrt(3) / 3 * length, 0);

        // Calculate the cosine and sine of the angle
        double cosAngle = Math.cos(Math.toRadians(angle));
        double sinAngle = Math.sin(Math.toRadians(angle));

        // Rotate the points around the specified axis
        switch (axis) {
            case X:
                this.v0 = rotatePointX(p0, cosAngle, sinAngle).add(center);
                this.v1 = rotatePointX(p1, cosAngle, sinAngle).add(center);
                this.v2 = rotatePointX(p2, cosAngle, sinAngle).add(center);
                break;
            case Y:
                this.v0 = rotatePointY(p0, cosAngle, sinAngle).add(center);
                this.v1 = rotatePointY(p1, cosAngle, sinAngle).add(center);
                this.v2 = rotatePointY(p2, cosAngle, sinAngle).add(center);
                break;
            case Z:
                this.v0 = rotatePointZ(p0, cosAngle, sinAngle).add(center);
                this.v1 = rotatePointZ(p1, cosAngle, sinAngle).add(center);
                this.v2 = rotatePointZ(p2, cosAngle, sinAngle).add(center);
                break;
        }
    }

    // Method to rotate a point around the X-axis
    private MyVec3 rotatePointX(MyVec3 point, double cosAngle, double sinAngle) {
        double y = point.getY() * cosAngle - point.getZ() * sinAngle;
        double z = point.getY() * sinAngle + point.getZ() * cosAngle;
        return new MyVec3(point.getX(), y, z);
    }

    // Method to rotate a point around the Y-axis
    private MyVec3 rotatePointY(MyVec3 point, double cosAngle, double sinAngle) {
        double x = point.getX() * cosAngle - point.getZ() * sinAngle;
        double z = point.getX() * sinAngle + point.getZ() * cosAngle;
        return new MyVec3(x, point.getY(), z);
    }

    // Method to rotate a point around the Z-axis
    private MyVec3 rotatePointZ(MyVec3 point, double cosAngle, double sinAngle) {
        double x = point.getX() * cosAngle - point.getY() * sinAngle;
        double y = point.getX() * sinAngle + point.getY() * cosAngle;
        return new MyVec3(x, y, point.getZ());
    }

    // Method to calculate the intersection of a ray with the triangle
    @Override
    public double getIntersection(MyVec3 origin, MyVec3 direction) {
        // Calculation of coefficients for intersection calculation
        double a = v0.getX() - v1.getX(), b = v0.getX() - v2.getX(), c = direction.getX(), d = v0.getX() - origin.getX();
        double e = v0.getY() - v1.getY(), f = v0.getY() - v2.getY(), g = direction.getY(), h = v0.getY() - origin.getY();
        double i = v0.getZ() - v1.getZ(), j = v0.getZ() - v2.getZ(), k = direction.getZ(), l = v0.getZ() - origin.getZ();

        // More calculations for intersection
        double m = f * k - g * j, n = h * k - g * l, p = f * l - h * j;
        double q = g * i - e * k, s = e * j - f * i;

        // Inverse of the denominator
        double invDenom = 1 / (a * m + b * q + c * s);

        // Intersection calculation
        double e1 = d * m - b * n - c * p;
        double beta = e1 * invDenom;

        if (beta < 0)
            return -1; // No intersection if beta is negative

        double r = e * l - h * i;
        double e2 = a * n + d * q + c * r;
        double gamma = e2 * invDenom;

        if (gamma < 0 || beta + gamma > 1)
            return -1; // No intersection if gamma is negative or beta + gamma > 1

        double e3 = a * p - b * r + d * s;
        double t = e3 * invDenom;

        if (t < 0.0001D)
            return -1; // No intersection if t is too small

        return t; // Return intersection parameter
    }

    // Method to get the normal vector of the triangle at a given intersection point
    @Override
    public MyVec3 getNormal(MyVec3 intersectionPoint) {
        // Calculate and normalize the normal vector
        MyVec3 normal = v1.sub(v0).crossProduct(v2.sub(v0));
        normal.normalize();
        return normal;
    }
}