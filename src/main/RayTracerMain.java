package main;

import scenes.*;
import utils.JavaTga;
import utils.MyColor;
import utils.MyVec3;

import java.util.Scanner;

public class RayTracerMain extends JavaTga {

    private static final int DEFAULT_MAX_DEPTH = 5;
    private static final int DEFAULT_WIDTH = 1280;
    private static final int DEFAULT_HEIGHT = 735;
    private static final int DEFAULT_SCENE_NUMBER = 1;
    private static final int MAX_SCENE_NUMBER = 6;
    private static final Scanner scanner = new Scanner(System.in);

    public static boolean IS_HDR_IMAGE = false;

    public static void main(String[] args)  {
        if(args.length > 0){
            System.err.println("\n<< No arguments are allowed. >>\n");
            System.exit(1);
        }
        // Prompt the user to choose the scene number, max depth, width, height, and if the image is HDR
        int sceneNumber = promptForSceneNumber();
        int maxDepth = promptForMaxDepth();
        IS_HDR_IMAGE = promptForHDR();
        int width = promptForDimension("width", DEFAULT_WIDTH);
        int height = promptForDimension("height", DEFAULT_HEIGHT);

        // Instantiate the chosen scene and generate the filename
        Scene scene = instantiateChosenScene(sceneNumber);
        String filename = generateFilename(scene, maxDepth, width, height);

        // Get the observer position and image plane distance from the scene and build the scene
        MyVec3 observerPosition = scene.getObserverPosition();
        double imagePlaneDistance = scene.getImagePlaneDistance();
        scene.buildScene();

        // Render the scene and save the image
        System.out.println("\n-- Rendering the scene... --\n");
        byte[] buffer = renderScene(scene, observerPosition, imagePlaneDistance, maxDepth, width, height);
        System.out.println("\n-- Rendering completed --\n");
        saveImage(filename, buffer, width, height);
    }

    /**
     * Renders the scene and returns the image buffer.
     *
     * @param scene             the scene
     * @param observerPosition  the observer position
     * @param imagePlaneDistance the image plane distance
     * @param maxDepth          the max depth of the recursion
     * @param width             the width of the image
     * @param height            the height of the image
     * @return the image buffer
     */
    private static byte[] renderScene(Scene scene, MyVec3 observerPosition, double imagePlaneDistance, int maxDepth, int width, int height) {
        byte[] buffer = new byte[3 * width * height];
        float[] bufferHDR = new float[3 * width * height];
        float maxValue = 0.0f;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int index = 3 * (row * width + col);
                MyVec3 direction = calculateDirection(col, row, width, height, imagePlaneDistance);
                MyColor color = scene.findColor(observerPosition, direction, maxDepth);
                if(IS_HDR_IMAGE){
                    maxValue = updateBufferHDR(bufferHDR, index, color, maxValue);
                }else{
                    buffer[index] =  (byte) color.getBlue();
                    buffer[index + 1] =  (byte) color.getGreen();
                    buffer[index + 2] = (byte)  color.getRed();
                }
            }
        }

        if(IS_HDR_IMAGE) {
            normalizeBufferHDR(buffer, bufferHDR, width, height, maxValue);
        }

        return buffer;
    }

    /**
     * Calculates the direction of the ray based on the column, row, width, height, and image plane distance.
     *
     * @param col                the column
     * @param row                the row
     * @param width              the width of the image
     * @param height             the height of the image
     * @param imagePlaneDistance the image plane distance
     * @return the direction of the ray
     */
    private static MyVec3 calculateDirection(int col, int row, int width, int height, double imagePlaneDistance) {
        return new MyVec3(
                ((double) col - (double) width / 2.0D) / (double) height,
                ((double) row - (double) height / 2.0D) / (double) height,
                -imagePlaneDistance);
    }

    /**
     * Updates the bufferHDR with the color values and returns the maximum value.
     *
     * @param bufferHDR the HDR buffer
     * @param index     the index
     * @param color     the color
     * @param maxValue  the maximum value
     * @return the maximum value
     */
    private static float updateBufferHDR(float[] bufferHDR, int index, MyColor color, float maxValue) {
        bufferHDR[index] = color.getBlue();
        if(color.getBlue() > maxValue){
            maxValue = color.getBlue();
        }
        bufferHDR[index + 1] = color.getGreen();
        if(color.getGreen() > maxValue){
            maxValue = color.getGreen();
        }
        bufferHDR[index + 2] = color.getRed();
        if(color.getRed() > maxValue){
            maxValue = color.getRed();
        }
        return maxValue;
    }

    /**
     * Normalizes the bufferHDR and updates the buffer with the normalized values.
     *
     * @param buffer    the buffer
     * @param bufferHDR the HDR buffer
     * @param width     the width of the image
     * @param height    the height of the image
     * @param maxValue  the maximum value
     */
    private static void normalizeBufferHDR(byte[] buffer, float[] bufferHDR, int width, int height, float maxValue) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int index = 3 * (row * width + col);
                buffer[index] = (byte) ((bufferHDR[index] / maxValue) * 255.0F);
                buffer[index + 1] = (byte) ((bufferHDR[index + 1] / maxValue) * 255.0F);
                buffer[index + 2] = (byte) ((bufferHDR[index + 2] / maxValue) * 255.0F);
            }
        }
    }


    /**
     * Prompts the user to choose a scene number.
     * If the input is invalid, a default scene number will be chosen.
     *
     * @return the chosen scene number
     */
    private static int promptForSceneNumber() {
        printSceneOptions();
        int sceneNumber = readIntInput("Enter the scene number: ");
        if (sceneNumber < 1 || sceneNumber > MAX_SCENE_NUMBER) {
            System.err.println("\t<< The scene number must be between 1 and " + MAX_SCENE_NUMBER + ", A Default scene will be chosen >>\n");
            return DEFAULT_SCENE_NUMBER;
        }
        return sceneNumber;
    }

    /**
     * Prompts the user to enter the max depth of the recursion.
     * If the input is invalid, a default max depth will be chosen.
     *
     * @return the chosen max depth
     */
    private static int promptForMaxDepth() {
        int maxDepth = readIntInput("Enter the max depth of the recursion: ");
        if (maxDepth <= 0) {
            System.err.println("\t<< The max depth must be greater than 0, A Default max depth will be chosen >>\n");
            return DEFAULT_MAX_DEPTH;
        }
        return maxDepth;
    }

    /**
     * Prompts the user to enter the width or height of the image.
     * If the input is invalid, a default width or height will be chosen.
     *
     * @param dimensionName the name of the dimension (width or height)
     * @param defaultValue  the default value of the dimension
     * @return the chosen dimension
     */
    private static int promptForDimension(String dimensionName, int defaultValue) {
        int dimension = readIntInput("Enter the " + dimensionName + " of the Image: ");
        if (dimension < defaultValue) {
            System.err.println("\t<< The " + dimensionName + " must be greater or equal to " + defaultValue + ", A Default " + dimensionName + " will be chosen >>\n");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
            return defaultValue;
        }
        return dimension;
    }

    /**
     * Prompts the user to choose if the image is HDR.
     *
     * @return true if the image is HDR, false otherwise
     */
    private static boolean promptForHDR() {
        System.out.println(">> Do you want an HDR image? (yes/no)");
        String input = scanner.next().toLowerCase();
        return input.equals("yes");
    }

    /**
     * Prints the scene options to the console.
     */
    private static void printSceneOptions() {
        System.out.println("--> Please choose a scene number from the following list:\n" +
                "\t1: Classic Scene : A simple scene with spheres and planes.\n" +
                "\t2: BackRoom Scene : A scene with a lot of spheres and dark weird colors.\n" +
                "\t3: Triangulus Scene : A scene with triangles and a sun.\n" +
                "\t4: CheckMate Scene : A scene with a lot of spheres and a check mate board.\n" +
                "\t5: DiscoMirrorBall Scene : A scene with a lot of spheres and a disco mirror ball.\n"+
                "\t6: WhatACube Scene : A scene with a cube and a plan.\n");
    }

    /**
     * Reads an integer input from the user.
     * If the input is invalid, -1 will be returned.
     *
     * @param prompt the prompt message
     * @return the integer input
     */
    private static int readIntInput(String prompt) {
        try {
            System.out.println(">> " + prompt);
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.next(); // clear the invalid input
            return -1;
        }
    }

    /**
     * Instantiates the chosen scene based on the scene number.
     *
     * @param sceneNumber the chosen scene number
     * @return the instantiated scene
     */
    private static Scene instantiateChosenScene(int sceneNumber) {
        switch (sceneNumber) {
            case 1:
                return new ClassicScene();
            case 2:
                return new BackRoomScene();
            case 3:
                return new TriangulusScene();
            case 4:
                return new CheckMateScene();
            case 5:
                return new DiscoMirrorBallScene();
            case 6:
                return new WhatACubeScene();
            default:
                throw new IllegalArgumentException("Invalid scene number: " + sceneNumber);
        }
    }

    /**
     * Generates the filename of the output image based on the scene, max depth, width, and height.
     *
     * @param scene    the scene
     * @param maxDepth the max depth of the recursion
     * @param width    the width of the image
     * @param height   the height of the image
     * @return the generated filename
     */
   private static String generateFilename(Scene scene, int maxDepth, int width, int height) {
        String hdrSuffix = IS_HDR_IMAGE ? "_HDR" : "";
        return "outputImages/" + scene.getSceneName() + hdrSuffix + "_" + maxDepth + "_" + width + "x" + height  + ".tga";
   }

    /**
     * Saves the image to a file.
     *
     * @param filename the filename
     * @param buffer   the image buffer
     * @param width    the width of the image
     * @param height   the height of the image
     */
    private static void saveImage(String filename, byte[] buffer, int width, int height) {
        try {
            saveTGA(filename, buffer, width, height);
            System.out.println("\n-- Saved image to " + filename + " --\n");
        } catch (Exception e) {
            System.err.println("<< Image could not be saved. >>");
        }
    }
}
