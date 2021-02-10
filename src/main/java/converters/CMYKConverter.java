package converters;

import colors.ColorObject;
import colors.ColorType;

/**
 * The type Cmyk converter.
 */
public class CMYKConverter {
    /**
     * Convert to color object.
     *
     * @param colorObject the color object
     * @param colorType   the color type
     * @return the color object
     * @throws Exception the exception
     */
    public static ColorObject convertTo(ColorObject colorObject, ColorType colorType) throws Exception {
        return switch (colorType) {
            case LAB -> new CMYKToLabConverter().convert(colorObject);
            case CMYK -> colorObject;
            case XYZ -> new CMYKToXYZConverter().convert(colorObject);
            case RGB -> new RGBToCMYKConverter().inverseConvert(colorObject);
        };
    }
}
