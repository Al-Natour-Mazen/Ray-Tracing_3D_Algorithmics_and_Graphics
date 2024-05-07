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
     * @param rayOrigin the origin (P) of the ray
     * @param rayDirection the direction (V) of the ray
     * @param recursionDepth the depth of the recursion
     * @return the color of the pixel
     */
    public MyColor findColor(MyVec3 rayOrigin, MyVec3 rayDirection, int recursionDepth) {
        if (recursionDepth == 0)
            return Ray.BASIC_AMBIENT_RAY;

        double closestIntersectionDistance = Double.MAX_VALUE;
        IntersectableObject closestIntersectedObject = null;

        for (IntersectableObject object : this.objects) {
            double intersectionDistance = object.getIntersection(rayOrigin, rayDirection);

            if (intersectionDistance > 0.0D && intersectionDistance < closestIntersectionDistance) {
                closestIntersectionDistance = intersectionDistance;
                closestIntersectedObject = object;
            }
        }

        if (closestIntersectedObject == null)
            // No intersection found -> return background color (black)
            return MyColor.black;

        IntersectableObjectDrawableOptions objectDrawOptions = closestIntersectedObject.getDrawOptions();
        MyVec3 intersectionPoint = closestIntersectedObject.getIntersectionPoint(rayOrigin, rayDirection, closestIntersectionDistance); // intersectionPoint = rayOrigin + closestIntersectionDistance * rayDirection
        MyVec3 normalAtIntersection = closestIntersectedObject.getNormal(intersectionPoint);

        boolean isInside = normalAtIntersection.dotProduct(rayDirection) > 0.0D;
        if (isInside){
            normalAtIntersection = normalAtIntersection.mul(-1.0D); // normalAtIntersection = -normalAtIntersection
        }

        MyColor color = objectDrawOptions.getColor(intersectionPoint).multiply(Ray.BASIC_AMBIENT_RAY); // object.color * ambientColor

        for (Ray light : this.lights) {
            MyVec3 lightDirection = light.getPosition().sub(intersectionPoint); // lightDirection = lightPosition - intersectionPoint

            boolean isVisible = true;

            for (IntersectableObject object : this.objects) {
                double intersectionDistance = object.getIntersection(intersectionPoint, lightDirection);

                if (0.0D < intersectionDistance && intersectionDistance < 1.0D)
                    isVisible = false;
            }

            if (isVisible) {
                normalAtIntersection.normalize(); // normalAtIntersection / ||normalAtIntersection||
                lightDirection.normalize(); // lightDirection / ||lightDirection||
                rayDirection.normalize(); // rayDirection / ||rayDirection||

                double dotProductNormalLight = Math.max(normalAtIntersection.dotProduct(lightDirection), 0.0D); // dotProductNormalLight = max(normalAtIntersection . lightDirection, 0)

                MyVec3 reflectionDirection = lightDirection.sub(normalAtIntersection.mul(2.0D * dotProductNormalLight)); // reflectionDirection = lightDirection - 2 * dotProductNormalLight * normalAtIntersection
                double dotProductReflectionRay = Math.max(reflectionDirection.dotProduct(rayDirection), 0.0D); // dotProductReflectionRay = max(reflectionDirection . rayDirection, 0)

                MyColor diffuse = light.getDiffuse().multiply(objectDrawOptions.getColor(intersectionPoint)).multiply(dotProductNormalLight); // light.diffuse * object.color * dotProductNormalLight
                MyColor specular = light.getSpecular().multiply(objectDrawOptions.getSpecularColor())
                        .multiply(Math.pow(dotProductReflectionRay, objectDrawOptions.getShininess())); // light.specular * object.specularColor * pow(dotProductReflectionRay,object.shininess)

                color = color.add(diffuse).add(specular);
            }
        }

        return color;
    }
}
