import scenes.BackRoomScene;
import scenes.ClassicScene;
import scenes.Scene;
import utils.JavaTga;
import utils.MyColor;
import utils.MyVec3;

public class RayTracerMain extends JavaTga {


    public static void main(String[] args) {
        int maxDepth = 1;
        int w = 1280;
        int h = 750;

        String filename = "outputImages/scene_" + maxDepth + "_" + w + "x" + h + ".tga";
        Scene scene = new BackRoomScene();
        MyVec3 P = scene.getObserverPosition();
        double D = scene.getImagePlaneDistance();
        scene.buildScene();

        byte[] buffer = new byte[3 * w * h];

        for (int row = 0; row < h; row++) { // for each row of the image
            for (int col = 0; col < w; col++) { // for each column of the image
                int index = 3 * (row * w + col);

                MyVec3 v = new MyVec3(
                        ((double) col - (double) w / 2.0D) / (double) h,
                        ((double) row - (double) h / 2.0D) / (double) h,
                        -D);

                MyColor color = scene.findColor(P, v, maxDepth);

                buffer[index] = color.getBlue();
                buffer[index + 1] = color.getGreen();
                buffer[index + 2] = color.getRed();
            }
        }
        try {
            saveTGA(filename, buffer, w, h);
            System.out.println("Saved image to " + filename);
        } catch (Exception e) {
            System.err.println("Image could not be saved.");
        }
    }


}