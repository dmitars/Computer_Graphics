package converters;

import colors.ColorObject;
import colors.RGBColor;
import colors.XYZColor;

import java.util.stream.Stream;

/**
 * The type Rgb to xyz converter.
 */
public class RGBToXYZConverter implements ColorConverter {

    /**
     * The Matrix.
     */
    double[][] matrix = {
            {0.412453, 0.357580, 0.180423},
            {0.212671, 0.715160, 0.072169},
            {0.019334, 0.119193, 0.950227}
    };

    /**
     * The Inverse matrix.
     */
    double[][] inverseMatrix = {
            {3.2406, -1.5372, -0.4986},
            {-0.9689, 1.8758, 0.0415},
            {0.0557, -0.2040, 1.0570}
    };

    @Override
    public ColorObject convert(ColorObject inputColor) throws Exception {
        if (!(inputColor instanceof RGBColor))
            throw new Exception("incorrect input color type");

        double[] rgbCoordinates = inputColor.getDoubleCoordinates();

        double[] nVector = new double[3];
        nVector[0] = F(rgbCoordinates[0] / 255) * 100;
        nVector[1] = F(rgbCoordinates[1] / 255) * 100;
        nVector[2] = F(rgbCoordinates[2] / 255) * 100;

        var xyzCoordinates = getMultiplication(matrix, nVector);
        XYZColor xyzColor = new XYZColor();
        xyzColor.setDoubleCoordinates(xyzCoordinates);

        return xyzColor;
    }

    @Override
    public ColorObject inverseConvert(ColorObject inputColor) throws Exception {
        if (!(inputColor instanceof XYZColor))
            throw new Exception("incorrect input color type");

        double[] xyzCoordinates = inputColor.getDoubleCoordinates();

        double[] bVector = new double[3];
        bVector[0] = xyzCoordinates[0] / 100;
        bVector[1] = xyzCoordinates[1] / 100;
        bVector[2] = xyzCoordinates[2] / 100;

        double[] nVector = getMultiplication(inverseMatrix, bVector);
        for (int i = 0; i < nVector.length; i++) {
            nVector[i] = inverseF(nVector[i]) * 255;
        }
        RGBColor rgbColor = new RGBColor();
        rgbColor.setDoubleCoordinates(nVector);
        return rgbColor;
    }

    private double[] getMultiplication(double[][] A, double[] b) {
        double[] result = new double[A.length];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i] += A[i][j] * b[j];
            }
        }
        return result;
    }

    private double F(double x) {
        return x >= 0.04045 ? Math.pow((x + 0.055) / 1.055, 2.4) : x / 12.92;
    }

    private double inverseF(double x) {
        return x >= 0.0031308 ? 1.055 * Math.pow(x, (double) 1 / 2.4) - 0.055 : 12.92 * x;
    }
}
