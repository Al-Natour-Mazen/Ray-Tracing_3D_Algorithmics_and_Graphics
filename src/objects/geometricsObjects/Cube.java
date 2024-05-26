package objects.geometricsObjects;

import objects.Axis;
import objects.IntersectableObject;
import objects.IntersectableObjectDrawableOptions;
import utils.MyColor;
import utils.MyVec3;

/**
 * @author : Mazen
 * @version : 1.0
 **/
public class Cube extends IntersectableObject {

    private MyVec3 min; // Minimum point of the cube
    private MyVec3 max; // Maximum point of the cube
    private final MyVec3[] vertices; // Vertices of the cube

    // Constructor to create a cube with specified options, center, and side length
    public Cube(IntersectableObjectDrawableOptions options, MyVec3 center, double side) {
        super(options);
        double halfSide = side / 2;
        vertices = new MyVec3[8];
        vertices[0] = new MyVec3(center.getX() - halfSide, center.getY() - halfSide, center.getZ() - halfSide);
        vertices[1] = new MyVec3(center.getX() + halfSide, center.getY() - halfSide, center.getZ() - halfSide);
        vertices[2] = new MyVec3(center.getX() + halfSide, center.getY() + halfSide, center.getZ() - halfSide);
        vertices[3] = new MyVec3(center.getX() - halfSide, center.getY() + halfSide, center.getZ() - halfSide);
        vertices[4] = new MyVec3(center.getX() - halfSide, center.getY() - halfSide, center.getZ() + halfSide);
        vertices[5] = new MyVec3(center.getX() + halfSide, center.getY() - halfSide, center.getZ() + halfSide);
        vertices[6] = new MyVec3(center.getX() + halfSide, center.getY() + halfSide, center.getZ() + halfSide);
        vertices[7] = new MyVec3(center.getX() - halfSide, center.getY() + halfSide, center.getZ() + halfSide);

        updateMinMax();
    }

    private void updateMinMax() {
        min = new MyVec3(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        max = new MyVec3(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

        for (MyVec3 vertex : vertices) {
            min = new MyVec3(Math.min(min.getX(), vertex.getX()), Math.min(min.getY(), vertex.getY()), Math.min(min.getZ(), vertex.getZ()));
            max = new MyVec3(Math.max(max.getX(), vertex.getX()), Math.max(max.getY(), vertex.getY()), Math.max(max.getZ(), vertex.getZ()));
        }
    }

    public void rotateCube(double angle, Axis axis, MyVec3 center) {
        double rad = Math.toRadians(angle);

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = vertices[i].subtract(center); // Translate to origin

            double x = vertices[i].getX();
            double y = vertices[i].getY();
            double z = vertices[i].getZ();

            switch (axis) {
                case X:
                    vertices[i] = new MyVec3(x,
                            y * Math.cos(rad) - z * Math.sin(rad),
                            y * Math.sin(rad) + z * Math.cos(rad));
                    break;
                case Y:
                    vertices[i] = new MyVec3(z * Math.sin(rad) + x * Math.cos(rad),
                            y,
                            z * Math.cos(rad) - x * Math.sin(rad));
                    break;
                case Z:
                    vertices[i] = new MyVec3(x * Math.cos(rad) - y * Math.sin(rad),
                            x * Math.sin(rad) + y * Math.cos(rad),
                            z);
                    break;
            }

            vertices[i] = vertices[i].add(center); // Translate back to original position
        }

        updateMinMax();
    }

    @Override
    public double getIntersection(MyVec3 origin, MyVec3 direction) {
        double tMin = (min.getX() - origin.getX()) / direction.getX();
        double tMax = (max.getX() - origin.getX()) / direction.getX();

        if (tMin > tMax) {
            double temp = tMin;
            tMin = tMax;
            tMax = temp;
        }

        double tyMin = (min.getY() - origin.getY()) / direction.getY();
        double tyMax = (max.getY() - origin.getY()) / direction.getY();

        if (tyMin > tyMax) {
            double temp = tyMin;
            tyMin = tyMax;
            tyMax = temp;
        }

        if ((tMin > tyMax) || (tyMin > tMax))
            return -1;

        if (tyMin > tMin)
            tMin = tyMin;

        if (tyMax < tMax)
            tMax = tyMax;

        double tzMin = (min.getZ() - origin.getZ()) / direction.getZ();
        double tzMax = (max.getZ() - origin.getZ()) / direction.getZ();

        if (tzMin > tzMax) {
            double temp = tzMin;
            tzMin = tzMax;
            tzMax = temp;
        }

        if ((tMin > tzMax) || (tzMin > tMax))
            return -1;

        if (tzMin > tMin)
            tMin = tzMin;

        if (tzMax < tMax)
            tMax = tzMax;

        return tMin;
    }

    @Override
    public MyVec3 getNormal(MyVec3 I) {
        double epsilon = 0.0001;
        if (Math.abs(I.getX() - min.getX()) < epsilon)
            return new MyVec3(-1, 0, 0);
        else if (Math.abs(I.getX() - max.getX()) < epsilon)
            return new MyVec3(1, 0, 0);
        else if (Math.abs(I.getY() - min.getY()) < epsilon)
            return new MyVec3(0, -1, 0);
        else if (Math.abs(I.getY() - max.getY()) < epsilon)
            return new MyVec3(0, 1, 0);
        else if (Math.abs(I.getZ() - min.getZ()) < epsilon)
            return new MyVec3(0, 0, -1);
        else
            return new MyVec3(0, 0, 1);
    }

    @Override
    public MyColor getTextureColor(MyVec3 I) {
        // Calculate the local coordinates of the intersection point
        MyVec3 localI = I.subtract(min);

        // Calculate the size of the cube
        MyVec3 size = max.subtract(min);

        // Calculate the normalized local coordinates
        MyVec3 normalizedI = new MyVec3(localI.getX() / size.getX(), localI.getY() / size.getY(), localI.getZ() / size.getZ());

        // Determine which face of the cube is intersected
        MyVec3 normal = getNormal(I);
        double u, v;
        if (normal.equals(new MyVec3(-1, 0, 0)) || normal.equals(new MyVec3(1, 0, 0))) {
            // Left or right face
            u = normalizedI.getZ();
            v = normalizedI.getY();
        } else if (normal.equals(new MyVec3(0, -1, 0)) || normal.equals(new MyVec3(0, 1, 0))) {
            // Bottom or top face
            u = normalizedI.getX();
            v = normalizedI.getZ();
        } else {
            // Front or back face
            u = normalizedI.getX();
            v = normalizedI.getY();
        }

        // Ensure texture coordinates are within the bounds of the texture
        u = u % 1.0;
        v = v % 1.0;

        // Get color from texture
        return drawOptions.getTexture().getColor(u, v);
    }
}
