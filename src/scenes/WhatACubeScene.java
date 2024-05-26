package scenes;

import objects.Axis;
import objects.IntersectableObject;
import objects.entitiesObjects.BrickDrawableOptions;
import objects.entitiesObjects.WeirdDrawableOptions;
import objects.entitiesObjects.StripedDrawableOptions;
import objects.geometricsObjects.Cube;
import objects.geometricsObjects.Plan;
import rendering.Ray;
import utils.MyColor;
import utils.MyVec3;

/**
 * @author : Mazen
 * @version : 1.0
 */
public class WhatACubeScene extends Scene{

    @Override
    protected void initSceneParameters() {
        allowReflections = true;
    }

    @Override
    protected void initSceneObjects() {
        StripedDrawableOptions optionsPlan4 = new StripedDrawableOptions(MyColor.teal, MyColor.lightGray, 10.0D, MyColor.lightCyan ,0.7D);
        IntersectableObject plan = new Plan(optionsPlan4, new MyVec3(0.0D, 1.0D, 0.0D), 1.5D);

        // Creation of the cubes
        WeirdDrawableOptions optionsCube = new WeirdDrawableOptions(MyColor.brightPink, MyColor.white, 10.0D, MyColor.lightPink, 0.3D, 0.5D);
        MyVec3 center = new MyVec3(1D, -0.83D, -4.5D);
        Cube cube = new Cube(optionsCube, center, 1D);
        cube.rotateCube(-85, Axis.Y, new MyVec3(1D, -0.83D, -3.5D));

        WeirdDrawableOptions optionsCube2 = new WeirdDrawableOptions(MyColor.brightOlive, MyColor.white, 10.0D, MyColor.lightYellow, 0.1D, 0.6D);
        // Creation of the cube
        MyVec3 center2 = new MyVec3(-1D, -0.83D, -4.5D);
        Cube cube2 = new Cube(optionsCube2, center2, 1D);
        cube2.rotateCube(85, Axis.Y, new MyVec3(-1D, -0.83D, -3.5D));


        BrickDrawableOptions optionsBrick = new BrickDrawableOptions(MyColor.cyan, MyColor.white, 10.0D, MyColor.lightSkyBlue, 0.1D, 0.1D);
        MyVec3 center3 = new MyVec3(0D, -0.83D, -5.5D);
        Cube cube3 = new Cube(optionsBrick, center3, 1.2D);

        objects.add(plan);
        objects.add(cube);
        objects.add(cube2);
        objects.add(cube3);
    }

    @Override
    protected void initSceneLights() {
        Ray light = new Ray(new MyVec3(1D, 1D, 0D), MyColor.white, MyColor.lightGray);
        lights.add(light);
    }
}
