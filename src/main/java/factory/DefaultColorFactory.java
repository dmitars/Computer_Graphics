package factory;

import colors.*;

import java.awt.*;

/**
 * The type Default color factory.
 */
public class DefaultColorFactory {
    /**
     * Get color object with type color object.
     *
     * @param colorType the color type
     * @return the color object
     */
    public static ColorObject getColorObjectWithType(ColorType colorType){
        return switch (colorType){
            case CMYK -> new CMYKColor();
            case XYZ -> new XYZColor();
            case RGB -> new RGBColor();
            case LAB -> new LabColor();
        };
    }
}
