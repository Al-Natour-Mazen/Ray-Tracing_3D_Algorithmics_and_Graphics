package utils;

import static main.RayTracerMain.IS_HDR_IMAGE;

public class MyColor {

    static public final MyColor black = new MyColor(0.0F, 0.0F, 0.0F);
    static public final MyColor white = new MyColor(1.0F, 1.0F, 1.0F);
    static public final MyColor gray = new MyColor(0.5F, 0.5F, 0.5F);
    static public final MyColor lightgray = new MyColor(0.8F, 0.8F, 0.8F);
    static public final MyColor darkgray = new MyColor(0.2F, 0.2F, 0.2F);
    static public final MyColor red = new MyColor(1.F, 0.F, 0.F);
    static public final MyColor green = new MyColor(0.F, 1.F, 0.F);
    static public final MyColor blue = new MyColor(0.F, 0.F, 1.F);
    static public final MyColor yellow = new MyColor(1.F, 1.F, 0.F);
    static public final MyColor magenta = new MyColor(1.F, 0.F, 1.F);
    static public final MyColor cyan = new MyColor(0.F, 1.0F, 1.F);
    static public final MyColor orange = new MyColor(1.F, 0.5F, 0.F);
    static public final MyColor pink = new MyColor(1.F, 0.75F, 0.8F);
    static public final MyColor brown = new MyColor(0.65F, 0.16F, 0.16F);
    static public final MyColor purple = new MyColor(0.5F, 0.F, 0.5F);
    static public final MyColor olive = new MyColor(0.5F, 0.5F, 0.F);
    static public final MyColor teal = new MyColor(0.F, 0.5F, 0.5F);
    static public final MyColor navy = new MyColor(0.F, 0.F, 0.5F);
    static public final MyColor maroon = new MyColor(0.5F, 0.F, 0.F);
    static public final MyColor lime = new MyColor(0.5F, 1.F, 0.F);
    static public final MyColor indigo = new MyColor(0.29F, 0.F, 0.51F);
    static public final MyColor gold = new MyColor(1.F, 0.84F, 0.F);
    static public final MyColor silver = new MyColor(0.75F, 0.75F, 0.75F);
    static public final MyColor royalBlue = new MyColor(0.25F, 0.41F, 0.88F);
    static public final MyColor coral = new MyColor(1.F, 0.5F, 0.31F);
    static public final MyColor turquoise = new MyColor(0.25F, 0.88F, 0.82F);
    static public final MyColor violet = new MyColor(0.93F, 0.51F, 0.93F);
    static public final MyColor salmon = new MyColor(0.98F, 0.5F, 0.45F);
    static public final MyColor plum = new MyColor(0.87F, 0.63F, 0.87F);
    static public final MyColor orchid = new MyColor(0.85F, 0.44F, 0.84F);
    static public final MyColor beige = new MyColor(0.96F, 0.96F, 0.86F);
    static public final MyColor lavender = new MyColor(0.9F, 0.9F, 0.98F);
    static public final MyColor lightPink = new MyColor(1.F, 0.71F, 0.76F);
    static public final MyColor lightYellow = new MyColor(1.F, 1.F, 0.88F);
    static public final MyColor lightGreen = new MyColor(0.56F, 0.93F, 0.56F);
    static public final MyColor lightPurple = new MyColor(0.69F, 0.61F, 0.85F);
    static public final MyColor lightRed = new MyColor(1.F, 0.5F, 0.5F);
    static public final MyColor lightBlue = new MyColor(0.68F, 0.85F, 0.9F);
    static public final MyColor lightCoral = new MyColor(0.94F, 0.5F, 0.5F);
    static public final MyColor lightSkyBlue = new MyColor(0.53F, 0.81F, 0.98F);
    static public final MyColor lightGray = new MyColor(0.83F, 0.83F, 0.83F);
    static public final MyColor lightSalmon = new MyColor(1.F, 0.63F, 0.48F);
    static public final MyColor lightGoldenrodYellow = new MyColor(0.98F, 0.98F, 0.82F);
    static public final MyColor lightCyan = new MyColor(0.88F, 1.F, 1.F);
    static public final MyColor lightSteelBlue = new MyColor(0.69F, 0.77F, 0.87F);
    static public final MyColor brightRed = new MyColor(1.F, 0.F, 0.F);
    static public final MyColor brightGreen = new MyColor(0.F, 1.F, 0.F);
    static public final MyColor brightBlue = new MyColor(0.F, 0.F, 1.F);
    static public final MyColor brightYellow = new MyColor(1.F, 1.F, 0.F);
    static public final MyColor brightCyan = new MyColor(0.F, 1.F, 1.F);
    static public final MyColor brightMagenta = new MyColor(1.F, 0.F, 1.F);
    static public final MyColor brightOrange = new MyColor(1.F, 0.65F, 0.F);
    static public final MyColor brightPurple = new MyColor(0.5F, 0.F, 0.5F);
    static public final MyColor brightPink = new MyColor(1.F, 0.08F, 0.58F);
    static public final MyColor brightLime = new MyColor(0.5F, 1.F, 0.F);
    static public final MyColor brightTeal = new MyColor(0.2F, 0.8F, 1.8F);
    static public final MyColor brightBrown = new MyColor(0.65F, 0.35F, 0.13F);
    static public final MyColor brightOlive = new MyColor(0.6F, 0.6F, 0.2F);
    static public final MyColor brightIndigo = new MyColor(0.29F, 0.F, 0.51F);
    static public final MyColor brightGold = new MyColor(1.F, 0.84F, 0.F);
    static public final MyColor brightSilver = new MyColor(0.75F, 0.75F, 0.75F);
    static public final MyColor brightRoyalBlue = new MyColor(0.25F, 0.41F, 0.88F);
    static public final MyColor brightCoral = new MyColor(1.F, 0.5F, 0.31F);

    private final float r;
    private final float g;
    private final float b;

    /**
     * Constructor.
     *
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     */
    public MyColor(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     *
     * @return the red component
     */
    public float getRed() {
        if(IS_HDR_IMAGE){
            return this.r;
        }
        return (this.r * 255.0f);
    }

    /**
     *
     * @return the green component
     */
    public float getGreen() {
        if(IS_HDR_IMAGE){
            return this.g;
        }
        return (this.g * 255.0f);
    }

    /**
     *
     * @return the blue component
     */
    public float getBlue() {
        if(IS_HDR_IMAGE){
            return this.b;
        }
        return (this.b * 255.0f);
    }

    /**
     * Add a color to this color.
     *
     * @param c the color to add
     * @return the resulting color
     */
    public MyColor add(MyColor c) {
        float newR = this.r + c.r;
        float newG = this.g + c.g;
        float newB = this.b + c.b;

        if (!IS_HDR_IMAGE) {
            newR = Math.min(newR, 1.0f);
            newG = Math.min(newG, 1.0f);
            newB = Math.min(newB, 1.0f);
        }

        return new MyColor(newR, newG, newB);
    }


    /**
     * Multiply this color by a color.
     *
     * @param c the color to multiply by
     * @return the resulting color
     */
    public MyColor multiply(MyColor c) {
        return new MyColor(this.r * c.r, this.g * c.g, this.b * c.b);
    }

    /**
     * Multiply this color by a scalar.
     *
     * @param s the scalar
     * @return the resulting color
     */
    public MyColor multiply(double s) {
        return new MyColor(this.r * (float) s, this.g * (float) s, this.b * (float) s);
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

    /**
     * blend two colors together
     * @param color2 the second color
     * @param ratio the ratio of the first color
     * @return the blended color
     */
    public MyColor blend(MyColor color2, float ratio) {
        int red = (int)(this.r * ratio + color2.r * (1 - ratio));
        int green = (int)(this.g * ratio + color2.g * (1 - ratio));
        int blue = (int)(this.b * ratio + color2.b * (1 - ratio));
        return new MyColor(red, green, blue);
    }

    public MyColor lerp(MyColor endColor, double t) {
        float r = (float) (this.getRed() * (1 - t) + endColor.getRed() * t);
        float g = (float) (this.getGreen() * (1 - t) + endColor.getGreen() * t);
        float b = (float) (this.getBlue() * (1 - t) + endColor.getBlue() * t);
        return new MyColor(r, g, b);
    }

}
