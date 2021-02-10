package converters;

import colors.CMYKColor;
import colors.ColorObject;
import colors.XYZColor;

/**
 * The type Cmyk to xyz converter.
 */
public class CMYKToXYZConverter implements ColorConverter{
    private final RGBToCMYKConverter rgbToCMYKConverter  = new RGBToCMYKConverter();
    private final RGBToXYZConverter rgbToXYZConverter = new RGBToXYZConverter();

    @Override
    public ColorObject convert(ColorObject inputColor) throws Exception {
        if((!(inputColor instanceof CMYKColor)))
            throw new Exception("incorrect input color type");
        ColorObject rgbColor = rgbToCMYKConverter.inverseConvert(inputColor);
        var xyzColor = rgbToXYZConverter.convert(rgbColor);
        if(rgbColor.isCountedPrecisely())
            xyzColor.setCountedPrecisely(true);
        return xyzColor;
    }

    @Override
    public ColorObject inverseConvert(ColorObject inputColor) throws Exception {
        if((!(inputColor instanceof XYZColor)))
            throw new Exception("incorrect input color type");
        ColorObject rgbColor = rgbToXYZConverter.inverseConvert(inputColor);
        ColorObject cmykColor = rgbToCMYKConverter.convert(rgbColor);
        if(rgbColor.isCountedPrecisely())
            cmykColor.setCountedPrecisely(true);
        return cmykColor;
    }
}
