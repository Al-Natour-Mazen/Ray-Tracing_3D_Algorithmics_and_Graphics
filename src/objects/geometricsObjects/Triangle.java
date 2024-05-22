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

    public Triangle(IntersectableObjectDrawableOptions options, MyVec3 v0, MyVec3 v1, MyVec3 v2) {
        super(options);
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
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
        MyVec3 edge1 = v1.subtract(v0);
        MyVec3 edge2 = v2.subtract(v0);
        MyVec3 h = direction.crossProduct(edge2);
        double a = edge1.dotProduct(h);

        if (a > -0.00001 && a < 0.00001)
            return -1; // This ray is parallel to this triangle.

        double f = 1.0 / a;
        MyVec3 s = origin.subtract(v0);
        double u = f * s.dotProduct(h);

        if (u < 0.0 || u > 1.0)
            return -1;

        MyVec3 q = s.crossProduct(edge1);
        double v = f * direction.dotProduct(q);

        if (v < 0.0 || u + v > 1.0)
            return -1;

        // At this stage, we can compute t to find out where the intersection point is on the line.
        double t = f * edge2.dotProduct(q);

        if (t > 0.00001) // ray intersection
            return t;

        return -1; // This means that there is a line intersection but not a ray intersection.
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
