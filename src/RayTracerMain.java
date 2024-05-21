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
    private static final int MAX_SCENE_NUMBER = 5;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int sceneNumber = promptForSceneNumber();
        int maxDepth = promptForMaxDepth();
        int width = promptForDimension("width", DEFAULT_WIDTH);
        int height = promptForDimension("height", DEFAULT_HEIGHT);

        Scene scene = instantiateChosenScene(sceneNumber);
        String filename = generateFilename(scene, maxDepth, width, height);

        MyVec3 observerPosition = scene.getObserverPosition();
        double imagePlaneDistance = scene.getImagePlaneDistance();
        scene.buildScene();

        byte[] buffer = renderScene(scene, observerPosition, imagePlaneDistance, maxDepth, width, height);
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

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int index = 3 * (row * width + col);
                MyVec3 direction = new MyVec3(
                        ((double) col - (double) width / 2.0D) / (double) height,
                        ((double) row - (double) height / 2.0D) / (double) height,
                        -imagePlaneDistance);
                MyColor color = scene.findColor(observerPosition, direction, maxDepth);
                buffer[index] = color.getBlue();
                buffer[index + 1] = color.getGreen();
                buffer[index + 2] = color.getRed();
            }
        }

        return buffer;
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
            return defaultValue;
        }
        return dimension;
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
                "\t5: DiscoMirrorBall Scene : A scene with a lot of spheres and a disco mirror ball.");
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
        return "outputImages/" + scene.getSceneName() + "_" + maxDepth + "_" + width + "x" + height + ".tga";
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
