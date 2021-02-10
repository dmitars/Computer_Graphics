package converters;

import colors.CMYKColor;
import colors.ColorObject;
import colors.LabColor;

/**
 * The type Cmyk to lab converter.
 */
public class CMYKToLabConverter implements ColorConverter{
    private final CMYKToXYZConverter cmykToXYZConverter = new CMYKToXYZConverter();
    private final XYZToLabConverter xyzToLabConverter = new XYZToLabConverter();


    @Override
    public ColorObject convert(ColorObject inputColor) throws Exception {
        if(!(inputColor instanceof CMYKColor))
            throw new Exception("incorrect input color type");
        var xyzColor = cmykToXYZConverter.convert(inputColor);
        return xyzToLabConverter.convert(xyzColor);
    }

    @Override
    public ColorObject inverseConvert(ColorObject inputColor) throws Exception {
        if(!(inputColor instanceof LabColor))
            throw new Exception("incorrect input color type");
        var xyzColor = xyzToLabConverter.inverseConvert(inputColor);
        return cmykToXYZConverter.inverseConvert(xyzColor);
    }
}
