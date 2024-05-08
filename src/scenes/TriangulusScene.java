package scenes;

import objects.Axis;
import objects.IntersectableObject;
import objects.IntersectableObjectDrawableOptions;
import objects.geometricsObjects.Plan;
import objects.geometricsObjects.Triangle;
import rendering.Ray;
import utils.MyColor;
import utils.MyVec3;

public class TriangulusScene extends Scene{

    @Override
    protected void initObjects() {
        // Creation of the walls
        IntersectableObjectDrawableOptions optionsPlan = new IntersectableObjectDrawableOptions(MyColor.lightGray, MyColor.white, 10.0D);
        IntersectableObject leftWall = new Plan(optionsPlan, new MyVec3(1.0D, 0.0D, 0.0D), 3.0D);
        IntersectableObject rightWall = new Plan(optionsPlan, new MyVec3(-1.0D, 0.0D, 0.0D), 3.0D);
        IntersectableObject backWall = new Plan(optionsPlan, new MyVec3(0.0D, 1.0D, 0.0D), 1.5D);
        objects.add(leftWall);
        objects.add(rightWall);
        objects.add(backWall);


        // Craetion of the triangles
        IntersectableObjectDrawableOptions optionsTriangleSun = new IntersectableObjectDrawableOptions(MyColor.brightYellow, MyColor.white, 10.0D);
        MyVec3 centerSun = new MyVec3(1.7D, 0.3D, -5.0D); // Center pf the Sun
        IntersectableObjectDrawableOptions optionsTriangles = new IntersectableObjectDrawableOptions(MyColor.brightCyan, MyColor.white, 10.0D);
        MyVec3 centerTriangles = new MyVec3(-1.7D, -1.0D, -5.0D); // Center of the triangles
        for (int i = 0; i < 4; i++) {
            double angleSun = i * 90.0D - 45.0D; // Rotation angle for the Sun
            double angleTriangles =  i * 90.0D + 70.0D; // Rotation angle for the triangles
            IntersectableObject triangle = new Triangle(optionsTriangles, centerTriangles, 3.0D, -angleTriangles, Axis.Y);
            IntersectableObject Sun = new Triangle(optionsTriangleSun, centerSun, 2.0D, angleSun, Axis.Z);
            objects.add(triangle);
            objects.add(Sun);
        }

        

    }

    @Override
    protected void initLights() {
        // Creation of the lights
        Ray light = new Ray(new MyVec3(1D, 1D, 0D), MyColor.white, MyColor.lightGray);
        Ray light2 = new Ray(new MyVec3(2.5D, 2.3D, -7.0D), MyColor.brightRed, MyColor.black);
        lights.add(light);
        lights.add(light2);
    }

    @Override
    protected void initSceneParameters() {
        // we don't need to override this method
    }
}
