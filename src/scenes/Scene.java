package scenes;

import objects.IntersectableObjectDrawableOptions;
import objects.IntersectableObject;
import rendering.Ray;
import utils.MyColor;
import utils.MyVec3;

import java.util.ArrayList;

public abstract class Scene {

    protected final ArrayList<IntersectableObject> objects;
    protected final ArrayList<Ray> lights;
    private MyVec3 observerPosition = new MyVec3();
    private double imagePlaneDistance = 1D;

    /**
     * Constructor.
     */
    public Scene() {
        objects = new ArrayList<>();
        lights = new ArrayList<>();
    }

    /**
     * Build the scene.
     * With this method, the objects and lights of the scene are created.
     * the Observer position is set by default to (0, 0, 0) and the image plane distance to 1.
     */
    public void buildScene(){
        initLights();
        initObjects();
        if(objects.isEmpty() || lights.isEmpty())
            throw new IllegalArgumentException("The scene must contain at least one object and one light.");
    }

    /**
     * Initialize the objects of the scene.
     */
    protected abstract void initObjects();

    /**
     * Initialize the lights of the scene.
     */
    protected abstract void initLights();

    /**
     * @return the observer position
     */
    public MyVec3 getObserverPosition() {
        return observerPosition;
    }

    /**
     * @param observerPosition the observer position
     */
    public void setObserverPosition(MyVec3 observerPosition) {
        this.observerPosition = observerPosition;
    }

    /**
     * @return the image plane distance
     */
    public double getImagePlaneDistance() {
        return imagePlaneDistance;
    }

    /**
     * @param imagePlaneDistance the image plane distance
     */
    public void setImagePlaneDistance(double imagePlaneDistance) {
        this.imagePlaneDistance = imagePlaneDistance;
    }

    /**
     * Main method of the raytracer to find the color of a pixel.
     *
     * @param origin the origin (P) of the ray
     * @param direction the direction (V) of the ray
     * @param depth the depth of the recursion
     * @return the color of the pixel
     */
    public MyColor findColor(MyVec3 origin, MyVec3 direction, int depth) {
        if (depth == 0)
            return Ray.AMBIENT_LIGHT;

        double lambdaI = Double.MAX_VALUE;
        IntersectableObject objectI = null;

        for (IntersectableObject object : this.objects) {
            double lambdaObj = object.getIntersection(origin, direction);

            if (lambdaObj > 0.0D && lambdaObj < lambdaI) {
                lambdaI = lambdaObj;
                objectI = object;
            }
        }

        if (objectI == null)
            // No intersection found -> return background color (black)
            return MyColor.black;

        IntersectableObjectDrawableOptions objectIdrawOptions = objectI.getDrawOptions();
        MyVec3 I = objectI.getIntersectionPoint(origin, direction, lambdaI); // I = P + lambda * v
        MyVec3 nI = objectI.getNormal(I);

        boolean inside = nI.dotProduct(direction) > 0.0D;
        if (inside)
            nI = nI.mul(-1.0D);

        MyColor color = objectIdrawOptions.getColor(I).multiply(new MyColor(0, 0, 0)); // object.color * ambientColor

        for (Ray light : this.lights) {
            MyVec3 IS = light.getPosition().sub(I); // IS = S - I

            boolean visible = true;

            for (IntersectableObject object : this.objects) {
                double lambdaObj = object.getIntersection(I, IS);

                if (0.0D < lambdaObj && lambdaObj < 1.0D)
                    visible = false;
            }

            if (visible) {
                nI.normalize(); // nI / ||nI||
                IS.normalize(); // IS / ||IS||
                direction.normalize(); // v / ||v||

                double nIDotIS = Math.max(nI.dotProduct(IS), 0.0D); // niDotIS = max(nI . IS, 0)

                MyVec3 r = IS.sub(nI.mul(2.0D * nIDotIS)); // r = IS - 2 * nIDotIS * nI
                double rDotV = Math.max(r.dotProduct(direction), 0.0D); // rDotV = max(r . v, 0)

                MyColor diffuse = light.getDiffuse().multiply(objectIdrawOptions.getColor(I)).multiply(nIDotIS); // light.diffuse * object.color * niDotIS
                MyColor specular = light.getSpecular().multiply(objectIdrawOptions.getSpecularColor())
                        .multiply(Math.pow(rDotV, objectIdrawOptions.getShininess())); // light.specular * object.specularColor * pow(rDotV,object.shininess)

                color = color.add(diffuse).add(specular);
            }
        }

        return color;
    }

}
