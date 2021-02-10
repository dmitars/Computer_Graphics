package converters;

import colors.ColorObject;
import colors.ColorType;
import colors.LabColor;
import colors.XYZColor;

import java.util.stream.Stream;

/**
 * The type Xyz to lab converter.
 */
public class XYZToLabConverter implements ColorConverter {
    private final ColorType inputColorType = ColorType.XYZ;

    private final double Xw = 95.047;
    private final double Yw = 100;
    private final double Zw = 108.883;

    @Override
    public ColorObject convert(ColorObject inputColor) throws Exception {
        if (!(inputColor instanceof XYZColor))
            throw new Exception("incorrect color type");
        double[] xyzCoordinates = inputColor.getDoubleCoordinates();

        double[] labCoordinates = new double[3];
        final double xF = F(xyzCoordinates[0] / Xw);
        final double yF = F(xyzCoordinates[1] / Yw);
        final double zF = F(xyzCoordinates[2] / Xw);
        labCoordinates[0] = 116 * yF - 16;
        labCoordinates[1] = 500 * (xF - yF);
        labCoordinates[2] = 200 * (yF - zF);

        LabColor outputColor = new LabColor();
        outputColor.setDoubleCoordinates(labCoordinates);
        return outputColor;
    }

    @Override
    public ColorObject inverseConvert(ColorObject inputColor) throws Exception {
        if (!(inputColor instanceof LabColor))
            throw new Exception("incorrect color type");
        double[] labCoordinates = inputColor.getDoubleCoordinates();
        double[] xyzCoordinates = new double[3];
        xyzCoordinates[0] = inverseF(labCoordinates[1] / 500 + (labCoordinates[0] + 16) / 116) * Yw;
        xyzCoordinates[1] = inverseF((labCoordinates[0] + 16) / 116) * Xw;
        xyzCoordinates[2] = inverseF((labCoordinates[0] + 16) / 116 - labCoordinates[2] / 200) * Zw;
        XYZColor outputColor = new XYZColor();
        outputColor.setDoubleCoordinates(xyzCoordinates);
        return outputColor;
    }


    private double F(double x) {
        return x >= 0.008856 ? Math.pow(x, (double) 1 / 3) : 7.787 * x + (double) 16 / 116;
    }

    private double inverseF(double x) {
        double temp = Math.pow(x, 3);
        return temp >= 0.008856 ? temp : (x - (double) 16 / 116) / 7.787;
    }
}
