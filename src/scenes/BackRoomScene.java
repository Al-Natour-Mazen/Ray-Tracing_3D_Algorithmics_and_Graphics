package scenes;

import objects.IntersectableObject;
import objects.IntersectableObjectDrawableOptions;
import objects.geometricsObjects.Plan;
import objects.geometricsObjects.Sphere;
import rendering.Ray;
import utils.MyColor;
import utils.MyVec3;

public class BackRoomScene extends Scene {

    @Override
    protected void initObjects() {
        // Definition of the rendering options for Objects
        IntersectableObjectDrawableOptions wallOptions = new IntersectableObjectDrawableOptions(MyColor.lightGray, MyColor.lightGray, 20.0D);
        IntersectableObjectDrawableOptions sphereOptions = new IntersectableObjectDrawableOptions(MyColor.black, MyColor.white, 10.0D);

        // Creation of the walls
        IntersectableObject wall1 = new Plan(wallOptions, new MyVec3(0.0D, 10.0D, 0.0D), 10.0D);
        IntersectableObject wall2 = new Plan(wallOptions, new MyVec3(0.0D, -10.0D, 0.0D), 10.0D);
        IntersectableObject wall3 = new Plan(wallOptions, new MyVec3(10.0D, 0.0D, 0.0D), 10.0D);

        // Creation of the spheres
        IntersectableObject sphere1 = new Sphere(sphereOptions, new MyVec3(30.0D, 0.0D, -40.0D), 1.0D);
        IntersectableObject sphere2 = new Sphere(sphereOptions, new MyVec3(26.0D, 0.0D, -40.0D), 1.0D);

        // Adding objects to the scene's object list
        objects.add(wall1);
        objects.add(wall2);
        objects.add(wall3);
        objects.add(sphere1);
        objects.add(sphere2);
    }

    @Override
    protected void initLights() {
        Ray light = new Ray(new MyVec3(1D, 1D, 0D), MyColor.white, MyColor.lightGray);
        lights.add(light);
    }

    @Override
    protected void initSceneParameters() {
        // we don't need to override this method
    }
}