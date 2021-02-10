package converters;

import colors.ColorObject;
import colors.ColorType;

/**
 * The type Common converter.
 */
public class CommonConverter {
    /**
     * Convert color color object.
     *
     * @param colorObject the color object
     * @param inputType   the input type
     * @param outputType  the output type
     * @return the color object
     * @throws Exception the exception
     */
    public static ColorObject convertColor(ColorObject colorObject,
                                           ColorType inputType,
                                           ColorType outputType) throws Exception {
        return switch (inputType){
            case XYZ -> XYZConverter.convertTo(colorObject,outputType);
            case LAB -> LabConverter.convertTo(colorObject,outputType);
            case CMYK -> CMYKConverter.convertTo(colorObject,outputType);
            default -> throw new Exception("unexpected type");
        };
    }
}
