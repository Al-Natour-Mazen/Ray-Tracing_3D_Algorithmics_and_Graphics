package scenes;

import objects.IntersectableObjectDrawableOptions;
import objects.IntersectableObject;
import rendering.Ray;
import utils.MyColor;
import utils.MyVec3;

import java.util.ArrayList;

/**
 * @author : Mazen
 * @version : 1.0
 */
public abstract class Scene {

    protected final ArrayList<IntersectableObject> objects;
    protected final ArrayList<Ray> lights;

    // Parameters of the scene
    protected MyVec3 observerPosition = new MyVec3();
    protected double imagePlaneDistance = 1D;
    protected boolean allowReflections = false;
    protected boolean allowRefractions = false;

    /**
     * Constructor.
     */
    public Scene() {
        initSceneParameters();
        objects = new ArrayList<>();
        lights = new ArrayList<>();
    }

    /**
     * Build the scene.
     * With this method, the objects and lights of the scene are created.
     * the Observer position is set by default to (0, 0, 0) and the image plane distance to 1.
     */
    public void buildScene(){
        initSceneObjects();
        initSceneLights();
        if(objects.isEmpty() || lights.isEmpty())
            throw new IllegalArgumentException("The scene must contain at least one object and one light.");
    }

    /**
     * Initialize the parameters of the scene, such as Activation of reflections, refractions, setting the observer
     * position and the image plane distance etc.
     * By default, all parameters have default values. if you want to change them, you can override this method in the
     * subclass and set the parameters as you wish. otherwise, you can leave it as is.
     */
    protected abstract void initSceneParameters();

    /**
     * Initialize the objects of the scene.
     */
    protected abstract void initSceneObjects();

    /**
     * Initialize the lights of the scene.
     */
    protected abstract void initSceneLights();

    /**
     * @return the observer position
     */
    public MyVec3 getObserverPosition() {
        return observerPosition;
    }

    /**
     * @return the image plane distance
     */
    public double getImagePlaneDistance() {
        return imagePlaneDistance;
    }

    /**
     * @return the name of the scene (class name)
     */
    public String getSceneName() {
        return this.getClass().getSimpleName();
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

        MyColor color = objectDrawOptions.calculateIntersectionColor(intersectionPoint).multiply(Ray.BASIC_AMBIENT_RAY); // object.color * ambientColor


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

               // Calculate specular color
                MyColor specular = light.getSpecular().multiply(objectDrawOptions.getSpecularColor())
                        .multiply(Math.pow(dotProductReflectionRay, objectDrawOptions.getShininess()));

                // Check if the object has a texture
                if (objectDrawOptions.getTexture() != null) {
                    // Use the texture color instead of the object color
                    color = closestIntersectedObject.getTextureColor(intersectionPoint);
                    // Add specular color to the final color
                    color = color.add(specular);
                } else {
                    // Calculate diffuse color
                    MyColor diffuse = light.getDiffuse().multiply(objectDrawOptions.calculateIntersectionColor(intersectionPoint)).multiply(dotProductNormalLight);
                    // Add diffuse and specular colors to the final color
                    color = color.add(diffuse).add(specular);
                }
            }
        }

        // Reflection
        if(allowReflections){
            // Calculate the reflection coefficient
            double reflectionCoefficient = objectDrawOptions.getReflectionCoefficient();
            // If the reflection coefficient is greater than 0
            if (reflectionCoefficient > 0.0D) {
                // Calculate the reflection direction
                MyVec3 r = rayDirection.sub(normalAtIntersection.mul(2.0D * normalAtIntersection.dotProduct(rayDirection))); // r = v - 2 * nIDotV * nI
                r.normalize(); // r / ||r||
                color = color.add(findColor(intersectionPoint, r, recursionDepth - 1).multiply(reflectionCoefficient)); // color + reflectionCoefficient * findColor(intersectionPoint, r, recursionDepth - 1)
            }
        }

        // Refraction
        if(allowRefractions){
            // Calculate the transmission coefficient
            double transmissionCoefficient = objectDrawOptions.getTransmissionCoefficient();
            // If the transmission coefficient is greater than 0
            if (transmissionCoefficient > 0.0D) {
                // Calculate the refraction index
                double refractionIndex = objectDrawOptions.getRefractionIndex();
                // Calculate the eta value
                double eta = isInside ? refractionIndex : 1.0D / refractionIndex; // eta = inside ? refractionIndex : 1 / refractionIndex
                // Calculate the c1 and c2 values
                double c1 = -normalAtIntersection.dotProduct(rayDirection); // c1 = nI . v
                // Calculate the c2 value
                double c2 = Math.sqrt(1.0D - eta * eta * (1.0D - c1 * c1)); // c2 = sqrt(1 - eta^2 * (1 - c1^2))
                // Calculate the transmission direction
                MyVec3 t = rayDirection.mul(eta).add(normalAtIntersection.mul(eta * c1 - c2)); // t = eta * v + (eta * c1 - c2) * nI
                t.normalize(); // t / ||t||
                color = color.add(findColor(intersectionPoint, t, recursionDepth - 1).multiply(transmissionCoefficient));
            }
        }

        return color;
    }
}
