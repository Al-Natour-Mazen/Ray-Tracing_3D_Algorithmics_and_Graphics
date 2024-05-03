package objects;

import utils.Color;

public abstract class IntersectableObject  {
    private final Color color;
    private final Color specularColor;

    /**
     * Constructor.
     *
     * @param color             the color of the object
     * @param specularColor     the specular color of the object
     */
    public IntersectableObject (Color color, Color specularColor) {
        this.color = color;
        this.specularColor = specularColor;

    }
}
