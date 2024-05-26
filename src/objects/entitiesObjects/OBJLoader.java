package objects.entitiesObjects;

import objects.IntersectableObjectDrawableOptions;
import objects.geometricsObjects.Triangle;
import utils.MyVec3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Mazen
 * @version : 1.0
 **/
public class OBJLoader {
    private final List<MyVec3> vertices;
    private final List<Triangle> triangles;

    public OBJLoader() {
        vertices = new ArrayList<>();
        triangles = new ArrayList<>();
    }

    public List<Triangle> loadOBJ(String filePath, IntersectableObjectDrawableOptions options, MyVec3 desiredCenter) {
        MyVec3 center = new MyVec3(0D, 0D, 0D);
        int verticesCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("v ")) {
                    // Parse vertex
                    String[] parts = line.split("\\s+");
                    double x = Double.parseDouble(parts[1]);
                    double y = Double.parseDouble(parts[2]);
                    double z = Double.parseDouble(parts[3]);
                    MyVec3 vertex = new MyVec3(x, y, z);

                    // Scale the vertex
                    double scale = 1.0; // Change this value to scale the model
                    vertex = vertex.scale(scale);

                    vertices.add(vertex);

                    // Update center
                    center = center.add(vertex);
                    verticesCount++;
                } else if (line.startsWith("f ")) {
                    // Parse face
                    String[] parts = line.split("\\s+");
                    int v0Index = Integer.parseInt(parts[1].split("/")[0]) - 1;
                    int v1Index = Integer.parseInt(parts[2].split("/")[0]) - 1;
                    int v2Index = Integer.parseInt(parts[3].split("/")[0]) - 1;
                    MyVec3 v0 = vertices.get(v0Index);
                    MyVec3 v1 = vertices.get(v1Index);
                    MyVec3 v2 = vertices.get(v2Index);
                    triangles.add(new Triangle(options, v0, v1, v2));
                }
            }

            // Calculate actual center
            center = center.scale(1.0 / verticesCount);

            // Calculate translation vector to move the object to the desired center
            MyVec3 translation = desiredCenter.subtract(center);

            // Move all vertices to center the object at the desired center
            for (int i = 0; i < vertices.size(); i++) {
                vertices.set(i, vertices.get(i).add(translation));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return triangles;
    }
}
