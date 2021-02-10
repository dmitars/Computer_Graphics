package converters;

import colors.ColorObject;
import colors.ColorType;

/**
 * The type Lab converter.
 */
public class LabConverter {
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
            case LAB -> colorObject;
            case XYZ -> new XYZToLabConverter().inverseConvert(colorObject);
            case CMYK -> new CMYKToLabConverter().inverseConvert(colorObject);
            case RGB -> {
                var cmykColor = new CMYKToLabConverter().inverseConvert(colorObject);
                yield new RGBToCMYKConverter().inverseConvert(cmykColor);
            }
        };
    }
}
