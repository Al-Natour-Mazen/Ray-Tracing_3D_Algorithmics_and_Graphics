package scenes;

import objects.IntersectableObject;
import objects.IntersectableObjectDrawableOptions;
import objects.geometricsObjects.Cube;
import objects.geometricsObjects.Plan;
import rendering.Ray;
import utils.MyColor;
import utils.MyVec3;
import utils.Texture;

/**
 * @author : Mazen
 * @version : 1.0
 */
public class MinecraftScene extends Scene{
    @Override
    protected void initSceneParameters() {
        // we don't need to change the default parameters
    }

    @Override
    protected void initSceneObjects() {
        Texture texturePlan = new Texture("textures/mc_dirt_block.jpg");
        IntersectableObjectDrawableOptions optionsPlan = new IntersectableObjectDrawableOptions(texturePlan, MyColor.lightGray, 1D);
        IntersectableObject plan = new Plan(optionsPlan, new MyVec3(0.0D, 1.0D, 0.0D), 1.5D);

        createCube("textures/mc_villagerhead_block.jpg", new MyVec3(-0.2D, -0.83D, -5.5D));
        createCube("textures/mc_emerald_block.jpeg", new MyVec3(-2.5D, -0.83D, -5.5D));

        createNetherPortal();

        createCube("textures/mc_netherite_block.png", new MyVec3(8.5D, -0.83D, -20.5D));
        createCube("textures/mc_luckyblock_block.png", new MyVec3(10.5D, -0.83D, -20.5D));
        createCube("textures/mc_copper_block.png", new MyVec3(12.5D, -0.83D, -18.5D));
        createCube("textures/mc_goldblock_block.jpg", new MyVec3(-18.2D, -0.83D, -26.5D));
        createCube("textures/mc_bricks_block.jpeg", new MyVec3(-20.5D, -0.83D, -26.5D));

        objects.add(plan);
    }

    @Override
    protected void initSceneLights() {
        Ray light = new Ray(new MyVec3(1D, 1D, 0D), MyColor.white, MyColor.lightGray);
        lights.add(light);
    }

    /**
     * Create a cube with the specified texture and center.
     * @param texturePath the path of the texture
     * @param center the center of the cube
     */
    private void createCube(String texturePath, MyVec3 center) {
        Texture texture = new Texture(texturePath);
        IntersectableObjectDrawableOptions options = new IntersectableObjectDrawableOptions(texture, MyColor.gray, 1.0);
        Cube cube = new Cube(options, center, 1.2);
        objects.add(cube);
    }

    /**
     * Create the nether portal.
     */
    private void createNetherPortal() {
        for (int i = 3; i <= 6; i++) {
            for (int j = -1; j <= 4; j++) {
                String texturePath = "textures/mc_obsidian_block.png";
                if (j > 0 && j < 4 && i > 3 && i < 6) {
                    texturePath = "textures/mc_netherportal_block.png";
                }
                createCube(texturePath, new MyVec3(i, j - 0.83D, -20.5D));
            }
        }
    }
}
