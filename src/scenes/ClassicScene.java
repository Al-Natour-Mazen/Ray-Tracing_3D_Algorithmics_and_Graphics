package scenes;

import objects.IntersectableObject;
import objects.IntersectableObjectDrawableOptions;
import objects.geometricsObjects.Cube;
import objects.geometricsObjects.Plan;
import objects.geometricsObjects.Sphere;
import rendering.Ray;
import utils.MyColor;
import utils.MyVec3;

public class ClassicScene extends Scene{

    @Override
    protected void initObjects() {
        //creation of the plans
        IntersectableObjectDrawableOptions optionsPlan1 = new IntersectableObjectDrawableOptions(MyColor.red, MyColor.lightGray, 20.0D);
        IntersectableObject plan1 = new Plan(optionsPlan1, new MyVec3(0.0D, 0.0D, 1.0D), 6.0D);

        IntersectableObjectDrawableOptions optionsPlan2 = new IntersectableObjectDrawableOptions(MyColor.green, MyColor.lightGray, 20.0D);
        IntersectableObject plan2 = new Plan(optionsPlan2, new MyVec3(1.0D, 0.0D, 0.0D), 3.0D);

        IntersectableObjectDrawableOptions optionsPlan3 = new IntersectableObjectDrawableOptions(MyColor.coral, MyColor.lightGray, 10.0D);
        IntersectableObject plan3 = new Plan(optionsPlan3, new MyVec3(-1.0D, 0.0D, 0.0D), 3.0D);

        IntersectableObjectDrawableOptions optionsPlan4 = new IntersectableObjectDrawableOptions(MyColor.cyan, MyColor.lightGray, 10.0D);
        IntersectableObject plan4 = new Plan(optionsPlan4, new MyVec3(0.0D, 1.0D, 0.0D), 1.5D);

        IntersectableObjectDrawableOptions optionsPlan5 = new IntersectableObjectDrawableOptions(MyColor.magenta, MyColor.lightGray, 10.0D);
        IntersectableObject plan5 = new Plan(optionsPlan5, new MyVec3(0.0D, -1.0D, 0.0D), 1.5D);

        objects.add(plan1);
        objects.add(plan2);
        objects.add(plan3);
        objects.add(plan4);
        objects.add(plan5);

        // Creation of the spheres
        IntersectableObjectDrawableOptions optionsSphere1 = new IntersectableObjectDrawableOptions(MyColor.teal, MyColor.white, 10.0D);
        IntersectableObject sphere1 = new Sphere(optionsSphere1, new MyVec3(-2.0D, -0.5D, -4.0D), 0.5D);

        IntersectableObjectDrawableOptions optionsSphere2 = new IntersectableObjectDrawableOptions(MyColor.olive, MyColor.white, 10.0D);
        IntersectableObject sphere2 = new Sphere(optionsSphere2, new MyVec3(2.0D, 0.5D, -4.0D), 0.7D);

        IntersectableObjectDrawableOptions optionsSphere3 = new IntersectableObjectDrawableOptions(MyColor.indigo, MyColor.white, 10.0D);
        IntersectableObject sphere3 = new Sphere(optionsSphere3, new MyVec3(0.0D, -0.5D, -4.0D), 0.8D);

        IntersectableObjectDrawableOptions optionsSphere4 = new IntersectableObjectDrawableOptions(MyColor.brown, MyColor.white, 10.0D);
        IntersectableObject sphere4 = new Sphere(optionsSphere4, new MyVec3(0.3D, 0.7D, -1.5D), 0.2D);

        objects.add(sphere1);
        objects.add(sphere2);
        objects.add(sphere3);
        objects.add(sphere4);
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
