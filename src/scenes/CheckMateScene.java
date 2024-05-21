package scenes;

import objects.IntersectableObjectDrawableOptions;
import objects.entitiesObjects.DraughtboardDrawableOptions;
import objects.geometricsObjects.Plan;
import objects.geometricsObjects.Sphere;
import rendering.Ray;
import utils.MyColor;
import utils.MyVec3;

public class CheckMateScene extends Scene{

    @Override
    protected void initSceneParameters() {
        observerPosition = new MyVec3(2D, 4D, 20D);
        allowReflections = true;
    }

    @Override
    protected void initSceneObjects() {
        DraughtboardDrawableOptions options = new DraughtboardDrawableOptions(MyColor.black, MyColor.white, 1000.0D, MyColor.white, 0.3D);
        Plan draughtboard = new Plan(options, new MyVec3(0.0D, 1.0D, 0.0D), 0D);

        // Creation of the spheres
        IntersectableObjectDrawableOptions optionsSphere1 = new IntersectableObjectDrawableOptions(MyColor.brightRoyalBlue, MyColor.white,  1000.0D, 0.3D );
        Sphere sphere1 = new Sphere(optionsSphere1, new MyVec3(9D, 3D, -1D), 3D);

        IntersectableObjectDrawableOptions optionsSphere2 = new IntersectableObjectDrawableOptions(MyColor.brightLime, MyColor.white, 1000.0D, 0.6D);
        Sphere sphere2 = new Sphere(optionsSphere2, new MyVec3(2D, 3D, -0D), 3D);

        IntersectableObjectDrawableOptions optionsSphere3 = new IntersectableObjectDrawableOptions(MyColor.brightMagenta, MyColor.white, 1000.0D, 0.8D);
        Sphere sphere3 = new Sphere(optionsSphere3, new MyVec3(-4.5D, 3D, 1D), 3D);

        objects.add(draughtboard);
        objects.add(sphere1);
        objects.add(sphere2);
        objects.add(sphere3);
    }

    @Override
    protected void initSceneLights() {
        Ray light = new Ray(new MyVec3(0D, 20D, 20D), MyColor.white, MyColor.lightGray);
        lights.add(light);
    }
}
