package utils;

public class Color {

    static public final float[] black={0.0F,0.0F,0.0F,1.F};
    static public final float[] white={1.0F,1.0F,1.0F,1.F};
    static public final float[] gray={0.5F,0.5F,0.5F,1.F};
    static public final float[] lightgray={0.8F,0.8F,0.8F,1.F};
    static public final float[] darkgray={0.2F,0.2F,0.2F,1.F};
    static public final float[] red={1.F,0.F,0.F,1.F};
    static public final float[] green={0.F,1.F,0.F,1.F};
    static public final float[] blue={0.F,0.F,1.F,1.F};
    static public final float[] yellow={1.F,1.F,0.F,1.F};
    static public final float[] magenta={1.F,0.F,1.F,0.75F};
    static public final float[] cyan={0.F,1.0F,1.F,1.F};
    static public final float[] orange={1.F,0.5F,0.F,1.F};
    static public final float[] pink={1.F,0.75F,0.8F,1.F};
    static public final float[] brown={0.65F,0.16F,0.16F,1.F};
    static public final float[] purple={0.5F,0.F,0.5F,1.F};
    static public final float[] olive={0.5F,0.5F,0.F,1.F};
    static public final float[] teal={0.F,0.5F,0.5F,1.F};
    static public final float[] navy={0.F,0.F,0.5F,1.F};
    static public final float[] maroon={0.5F,0.F,0.F,1.F};
    static public final float[] lime={0.5F,1.F,0.F,1.F};
    static public final float[] indigo={0.29F,0.F,0.51F,1.F};
    static public final float[] gold={1.F,0.84F,0.F,1.F};
    static public final float[] silver={0.75F,0.75F,0.75F,1.F};
    static public final float[] royalBlue={0.25F,0.41F,0.88F,1.F};
    static public final float[] coral={1.F,0.5F,0.31F,1.F};
    static public final float[] turquoise={0.25F,0.88F,0.82F,1.F};
    static public final float[] violet={0.93F,0.51F,0.93F,1.F};
    static public final float[] salmon={0.98F,0.5F,0.45F,1.F};
    static public final float[] plum={0.87F,0.63F,0.87F,1.F};
    static public final float[] orchid={0.85F,0.44F,0.84F,1.F};
    static public final float[] beige={0.96F,0.96F,0.86F,1.F};
    static public final float[] lavender={0.9F,0.9F,0.98F,1.F};
    static public final float[] lightPink = {1.F, 0.71F, 0.76F, 1.F};
    static public final float[] lightYellow = {1.F, 1.F, 0.88F, 1.F};
    static public final float[] lightGreen = {0.56F, 0.93F, 0.56F, 1.F};
    static public final float[] lightBlue = {0.68F, 0.85F, 0.9F, 1.F};
    static public final float[] lightCoral = {0.94F, 0.5F, 0.5F, 1.F};
    static public final float[] lightSkyBlue = {0.53F, 0.81F, 0.98F, 1.F};
    static public final float[] lightGray = {0.83F, 0.83F, 0.83F, 1.F};
    static public final float[] lightSalmon = {1.F, 0.63F, 0.48F, 1.F};
    static public final float[] lightGoldenrodYellow = {0.98F, 0.98F, 0.82F, 1.F};
    static public final float[] lightCyan = {0.88F, 1.F, 1.F, 1.F};
    static public final float[] lightSteelBlue = {0.69F, 0.77F, 0.87F, 1.F};
    static public final float[] brightRed = {1.F, 0.F, 0.F, 1.F};
    static public final float[] brightGreen = {0.F, 1.F, 0.F, 1.F};
    static public final float[] brightBlue = {0.F, 0.F, 1.F, 1.F};
    static public final float[] brightYellow = {1.F, 1.F, 0.F, 1.F};
    static public final float[] brightCyan = {0.F, 1.F, 1.F, 1.F};
    static public final float[] brightMagenta = {1.F, 0.F, 1.F, 1.F};
    static public final float[] brightOrange = {1.F, 0.65F, 0.F, 1.F};
    static public final float[] brightPurple = {0.5F, 0.F, 0.5F, 1.F};
    static public final float[] brightPink = {1.F, 0.08F, 0.58F, 1.F};
    static public final float[] brightLime = {0.5F, 1.F, 0.F, 1.F};
    static public final float[] brightTeal = {0.2F, 0.8F, 1.8F, 1.F};
    static public final float[] brightBrown = {0.65F, 0.35F, 0.13F, 1.F};
    static public final float[] brightOlive = {0.6F, 0.6F, 0.2F, 1.F};
    static public final float[] brightIndigo = {0.29F, 0.F, 0.51F, 1.F};
    static public final float[] brightGold = {1.F, 0.84F, 0.F, 1.F};
    static public final float[] brightSilver = {0.75F, 0.75F, 0.75F, 1.F};
    static public final float[] brightRoyalBlue = {0.25F, 0.41F, 0.88F, 1.F};
    static public final float[] brightCoral = {1.F, 0.5F, 0.31F, 1.F};

    private float r;
    private float g;
    private float b;

    /**
     * Constructor.
     *
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     */
    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     *
     * @return the red component
     */
    public byte getRed() {
        return (byte) (this.r * 255.0f);
    }

    /**
     *
     * @return the green component
     */
    public byte getGreen() {
        return (byte) (this.g * 255.0f);
    }

    /**
     *
     * @return the blue component
     */
    public byte getBlue() {
        return (byte) (this.b * 255.0f);
    }

    /**
     * Add a color to this color.
     *
     * @param c the color to add
     * @return the resulting color
     */
    public Color add(Color c) {
        return new Color(Math.min(this.r + c.r, 1.0f), Math.min(this.g + c.g, 1.0f), Math.min(this.b + c.b, 1.0f));
    }

    /**
     * Multiply this color by a color.
     *
     * @param c the color to multiply by
     * @return the resulting color
     */
    public Color multiply(Color c) {
        return new Color(this.r * c.r, this.g * c.g, this.b * c.b);
    }

    /**
     * Multiply this color by a scalar.
     *
     * @param s the scalar
     * @return the resulting color
     */
    public Color multiply(double s) {
        return new Color(this.r * (float) s, this.g * (float) s, this.b * (float) s);
    }

    /**
     * This method converts an integer representation of an RGB color to a float array.
     * The integer is assumed to be in the format 0xRRGGBB where RR, GG, and BB are two digit hexadecimal values.
     * The resulting float array contains the red, green, blue, and alpha components of the color, each as a float in the range [0, 1].
     * The alpha component is always set to 1.
     *
     * @param rgb the integer representation of the RGB color
     * @return a float array with the red, green, blue, and alpha components of the color
     */
    public static float[] getRGB(int rgb){
        float r=(rgb>>16)&0xFF;
        float g=(rgb>>8)&0xFF;
        float b=rgb&0xFF;
        return new float[]{r/255.0F,g/255.0F,b/255.0F,1.0F};
    }
}
