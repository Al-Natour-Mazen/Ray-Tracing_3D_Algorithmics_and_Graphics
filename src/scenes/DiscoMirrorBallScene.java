package scenes;

import objects.IntersectableObjectDrawableOptions;
import objects.entitiesObjects.DraughtboardDrawableOptions;
import objects.geometricsObjects.Plan;
import objects.geometricsObjects.Sphere;
import rendering.Ray;
import utils.MyColor;
import utils.MyVec3;

/**
 * @author : Mazen
 * @version : 1.0
 */
public class DiscoMirrorBallScene extends Scene{
    @Override
    protected void initSceneParameters() {
        observerPosition = new MyVec3(-2.6, 1.5, 3.9);
        imagePlaneDistance = 0.5D;
        allowReflections = true;
        allowRefractions = true;
    }

    @Override
    protected void initSceneObjects() {
        // Creation of the plans
        DraughtboardDrawableOptions options = new DraughtboardDrawableOptions(MyColor.green, MyColor.white, 1000.0D, MyColor.lime, 0.3D);
        Plan draughtboard = new Plan(options, new MyVec3(0.0D, 1.0D, 0.0D), 0D);

        IntersectableObjectDrawableOptions optionsPlan1 = new IntersectableObjectDrawableOptions(MyColor.gray, MyColor.white, 1000.0D);
        Plan plan1 = new Plan(optionsPlan1, new MyVec3(0.0D, -1.0D, 0.0D), 5.0D);

        IntersectableObjectDrawableOptions optionsPlan2 = new IntersectableObjectDrawableOptions(MyColor.gray, MyColor.white, 1000.0D, 0.3D);
        Plan plan2 = new Plan(optionsPlan2, new MyVec3(1.0D, 0.0D, 0.0D), 5.0D);

        IntersectableObjectDrawableOptions optionsPlan3 = new IntersectableObjectDrawableOptions(MyColor.gray, MyColor.white, 1000.0D, 0.3D);
        Plan plan3 = new Plan(optionsPlan3, new MyVec3(-1.0D, 0.0D, 0.0D), 5.0D);

        IntersectableObjectDrawableOptions optionsPlan4 = new IntersectableObjectDrawableOptions(MyColor.gray, MyColor.white, 1000.0D, 0.3D);
        Plan plan4 = new Plan(optionsPlan4, new MyVec3(0.0D, 0.0D, 1.0D), 5.0D);

        IntersectableObjectDrawableOptions optionsPlan5 = new IntersectableObjectDrawableOptions(MyColor.gray, MyColor.white, 1000.0D, 0.3D);
        Plan plan5 = new Plan(optionsPlan5, new MyVec3(0.0D, 0.0D, -1.0D), 5.0D);

        // Creation of the spheres
        IntersectableObjectDrawableOptions optionsSphere1 = new IntersectableObjectDrawableOptions(MyColor.coral, MyColor.white,  50.0D);
        Sphere sphere1 = new Sphere(optionsSphere1, new MyVec3(4.6D, 0.4D, -1.0D), 0.4D);

        IntersectableObjectDrawableOptions optionsSphere2 = new IntersectableObjectDrawableOptions(MyColor.brightTeal, MyColor.white,  50.0D);
        Sphere sphere2 = new Sphere(optionsSphere2, new MyVec3(4.7D, 0.3D, -0.4D), 0.3D);

        IntersectableObjectDrawableOptions optionsSphere3 = new IntersectableObjectDrawableOptions(MyColor.plum, MyColor.white,  50.0D);
        Sphere sphere3 = new Sphere(optionsSphere3, new MyVec3(-1.0D, 0.5D, -4.5D), 0.5D);

        IntersectableObjectDrawableOptions optionsSphere4 = new IntersectableObjectDrawableOptions(MyColor.turquoise, MyColor.white,  50.0D);
        Sphere sphere4 = new Sphere(optionsSphere4, new MyVec3(-1.7D, 0.3D, -4.7D), 0.3D);

        IntersectableObjectDrawableOptions optionsSphere5 = new IntersectableObjectDrawableOptions(MyColor.purple, MyColor.white,  50.0D);
        Sphere sphere5 = new Sphere(optionsSphere5, new MyVec3(-0.6D, 1.0D, -0.6D), 1.0D);

        IntersectableObjectDrawableOptions optionsSphere6 = new IntersectableObjectDrawableOptions(MyColor.lightPink, MyColor.white,  300.0D, 0.5D, 0.2D, 3.5D);
        Sphere sphere6 = new Sphere(optionsSphere6, new MyVec3(0.3D, 0.7D, 0.9D), 0.7D);

        IntersectableObjectDrawableOptions optionsSphere7 = new IntersectableObjectDrawableOptions(MyColor.brightMagenta, MyColor.white,  300.0D, 0.5D, 0.2D, 1.5D);
        Sphere sphere7 = new Sphere(optionsSphere7, new MyVec3(-2.7D, 0.5D, 0.8D), 0.5D);

        objects.add(draughtboard);
        objects.add(plan1);
        objects.add(plan2);
        objects.add(plan3);
        objects.add(plan4);
        objects.add(plan5);
        objects.add(sphere1);
        objects.add(sphere2);
        objects.add(sphere3);
        objects.add(sphere4);
        objects.add(sphere5);
        objects.add(sphere6);
        objects.add(sphere7);
    }

    @Override
    protected void initSceneLights() {
        Ray light = new Ray(new MyVec3(-4.9D, 4.9D, 1.0D), MyColor.white, MyColor.lightGray);
        lights.add(light);
    }
}
