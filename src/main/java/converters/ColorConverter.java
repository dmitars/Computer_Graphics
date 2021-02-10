package converters;

import colors.ColorObject;

/**
 * The interface Color converter.
 */
public interface ColorConverter {
    /**
     * Convert color object.
     *
     * @param inputColor the input color
     * @return the color object
     * @throws Exception the exception
     */
    ColorObject convert(ColorObject inputColor) throws Exception;

    /**
     * Inverse convert color object.
     *
     * @param inputColor the input color
     * @return the color object
     * @throws Exception the exception
     */
    ColorObject inverseConvert(ColorObject inputColor) throws Exception;
}
