package converters;

import colors.CMYKColor;
import colors.ColorObject;
import colors.RGBColor;

/**
 * The type Rgb to cmyk converter.
 */
public class RGBToCMYKConverter implements ColorConverter {
    @Override
    public ColorObject convert(ColorObject inputColor) throws Exception {
        if (!(inputColor instanceof RGBColor))
            throw new Exception("incorrect input color type");

        double[] rgbCoordinates = inputColor.getDoubleCoordinates();

        double[] cmykCoordinates = new double[4];
        cmykCoordinates[3] = Math.min(1 - rgbCoordinates[0] / 255, Math.min(1 - rgbCoordinates[1] / 255, 1 - rgbCoordinates[2] / 255));
        for (int i = 0; i < 3; i++) {
            cmykCoordinates[i] = (1 - rgbCoordinates[i] / 255 - cmykCoordinates[3]) / (1 - cmykCoordinates[3]);
        }

        CMYKColor cmykColor = new CMYKColor();
        cmykColor.setDoubleCoordinates(cmykCoordinates);
        return cmykColor;
    }

    @Override
    public ColorObject inverseConvert(ColorObject inputColor) throws Exception {
        if (!(inputColor instanceof CMYKColor))
            throw new Exception("incorrect input color type");
        double[] cmykCoordinates = inputColor.getDoubleCoordinates();
        double[] rgbCoordinates = new double[3];

        for (int i = 0; i < 3; i++) {
            rgbCoordinates[i] = 255 * (1 - cmykCoordinates[i]) * (1 - cmykCoordinates[3]);
        }

        RGBColor rgbColor = new RGBColor();
        rgbColor.setDoubleCoordinates(rgbCoordinates);
        return rgbColor;
    }
}
