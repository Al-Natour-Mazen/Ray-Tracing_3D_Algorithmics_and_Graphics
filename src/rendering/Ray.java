package rendering;

import utils.MyColor;
import utils.MyVec3;

public class Ray {

    private final MyVec3 position;
    private final MyColor diffuse;
    private final MyColor specular;

    public static MyColor BASIC_AMBIENT_RAY = MyColor.black;

    /**
     * Constructor.
     *
     * @param position the position of the Ray
     * @param diffuse  the diffuse color of the Ray
     * @param specular the specular color of the Ray
     */
    public Ray(MyVec3 position, MyColor diffuse, MyColor specular) {
        this.position = position;
        this.diffuse = diffuse;
        this.specular = specular;
    }

    /**
     *
     * @return the position of the light
     */
    public MyVec3 getPosition() {
        return position;
    }

    /**
     *
     * @return the diffuse color of the light
     */
    public MyColor getDiffuse() {
        return diffuse;
    }

    /**
     *
     * @return the specular color of the light
     */
    public MyColor getSpecular() {
        return specular;
    }
}
