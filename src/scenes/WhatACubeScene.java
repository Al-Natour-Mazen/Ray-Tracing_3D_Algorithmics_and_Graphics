package scenes;

import objects.Axis;
import objects.IntersectableObject;
import objects.IntersectableObjectDrawableOptions;
import objects.geometricsObjects.Cube;
import objects.geometricsObjects.Plan;
import rendering.Ray;
import utils.MyColor;
import utils.MyVec3;

public class WhatACubeScene extends Scene{

    @Override
    protected void initSceneParameters() {
        // we don't need to override this method
    }

    @Override
    protected void initSceneObjects() {
        IntersectableObjectDrawableOptions optionsPlan4 = new IntersectableObjectDrawableOptions(MyColor.teal, MyColor.lightGray, 10.0D);
        IntersectableObject plan = new Plan(optionsPlan4, new MyVec3(0.0D, 1.0D, 0.0D), 1.5D);

        // Creation of the cubes
        IntersectableObjectDrawableOptions optionsCube = new IntersectableObjectDrawableOptions(MyColor.brightPink, MyColor.white, 10.0D);
        MyVec3 center = new MyVec3(1D, -0.8D, -4.5D);
        Cube cube = new Cube(optionsCube, center, 1D);
        cube.rotateCube(-85, Axis.Y, new MyVec3(1D, -0.8D, -3.5D));

        IntersectableObjectDrawableOptions optionsCube2 = new IntersectableObjectDrawableOptions(MyColor.brightOlive, MyColor.white, 10.0D);
        // Creation of the cube
        MyVec3 center2 = new MyVec3(-1D, -0.8D, -4.5D);
        Cube cube2 = new Cube(optionsCube2, center2, 1D);
        cube2.rotateCube(85, Axis.Y, new MyVec3(-1D, -0.8D, -3.5D));

        IntersectableObjectDrawableOptions optionsCube3 = new IntersectableObjectDrawableOptions(MyColor.lightSkyBlue, MyColor.white, 10.0D);
        MyVec3 center3 = new MyVec3(0D, -0.8D, -5.5D);
        Cube cube3 = new Cube(optionsCube3, center3, 1D);

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
