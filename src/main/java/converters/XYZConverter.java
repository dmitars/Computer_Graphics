package converters;

import colors.ColorObject;
import colors.ColorType;

/**
 * The type Xyz converter.
 */
public class XYZConverter {
    /**
     * Convert to color object.
     *
     * @param colorObject the color object
     * @param colorType   the color type
     * @return the color object
     * @throws Exception the exception
     */
    public static ColorObject convertTo(ColorObject colorObject, ColorType colorType) throws Exception {
        return switch (colorType){
            case XYZ -> colorObject;
            case CMYK -> new CMYKToXYZConverter().inverseConvert(colorObject);
            case LAB -> new XYZToLabConverter().convert(colorObject);
            case RGB -> new RGBToXYZConverter().inverseConvert(colorObject);
        };
    }
}
