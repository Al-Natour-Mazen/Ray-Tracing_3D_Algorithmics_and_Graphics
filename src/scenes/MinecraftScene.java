package scenes;

import objects.IntersectableObject;
import objects.IntersectableObjectDrawableOptions;
import objects.entitiesObjects.BrickDrawableOptions;
import objects.entitiesObjects.StripedDrawableOptions;
import objects.geometricsObjects.Cube;
import objects.geometricsObjects.Plan;
import rendering.Ray;
import utils.MyColor;
import utils.MyVec3;
import utils.Texture;

public class MinecraftScene extends Scene{
    @Override
    protected void initSceneParameters() {
      // we don't need to change the default parameters
    }

    @Override
    protected void initSceneObjects() {
        Texture texturePlan = new Texture("textures/mc_dirt_block.jpg");
        IntersectableObjectDrawableOptions optionsPlan = new IntersectableObjectDrawableOptions(texturePlan, 50D);
        IntersectableObject plan = new Plan(optionsPlan, new MyVec3(0.0D, 1.0D, 0.0D), 1.5D);

        Texture textureCube = new Texture("textures/mc_goldblock_block.jpg");
        IntersectableObjectDrawableOptions optionsCube = new IntersectableObjectDrawableOptions(textureCube, 50D);
        MyVec3 center = new MyVec3(0D, -0.83D, -5.5D);
        Cube cube = new Cube(optionsCube, center, 1.2D);

        objects.add(plan);
        objects.add(cube);
    }

    @Override
    protected void initSceneLights() {
        Ray light = new Ray(new MyVec3(1D, 1D, 0D), MyColor.white, MyColor.white);
        lights.add(light);
    }
}
