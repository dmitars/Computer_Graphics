package util;

/**
 * The type Color utils.
 */
public class ColorUtils {
    /**
     * Gets brightness.
     *
     * @param color the color
     * @return the brightness
     */
    public static double getBrightness(int color) {
        int red = (color >>> 16) & 0xFF;
        int green = (color >>> 8) & 0xFF;
        int blue = (color) & 0xFF;
        return (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;
    }

    /**
     * Gets grayscale pixel.
     *
     * @param color the color
     * @return the grayscale pixel
     */
    public static int getGrayscalePixel(int color) {
        int red = (color >>> 16) & 0xFF;
        int green = (color >>> 8) & 0xFF;
        int blue = (color) & 0xFF;

        return (int) ((0.21 * red) + (0.71 * green) + (0.07 * blue));
    }
}
