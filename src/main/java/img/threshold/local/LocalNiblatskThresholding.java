package img.threshold.local;

import img.threshold.Interval;
import util.ColorUtils;
import util.IntervalUtils;

import java.awt.image.BufferedImage;

/**
 * The type Local niblatsk thresholding.
 */
public class LocalNiblatskThresholding extends LocalThresholding {
    private static final double k = -0.2;
    private static final int r = 15;

    /**
     * The Main pixel.
     */
    int mainPixel;

    /**
     * Instantiates a new Local niblatsk thresholding.
     *
     * @param bufferedImage the buffered image
     */
    public LocalNiblatskThresholding(BufferedImage bufferedImage) {
        super(bufferedImage);
    }


    @Override
    public double getThreshold(int x, int y) {
        var interval = getIntervalOfPoints(x,y);

        int mainColor = bufferedImage.getRGB(x, y);
        double avg = 0;
        double sqrAvg = 0;
        mainPixel = ColorUtils.getGrayscalePixel(mainColor);

        for (int i = interval.getXBase(); i <= interval.getXMax(); i++) {
            for (int j = interval.getYBase(); j <= interval.getYMax(); j++) {
                int color = bufferedImage.getRGB(i, j);
                int grayscalePixel = ColorUtils.getGrayscalePixel(color);
                avg += grayscalePixel;
                sqrAvg += (Math.pow(grayscalePixel - mainPixel, 2));
            }
        }

        int nodeNumber = interval.getHeight() * interval.getWidth();
        avg /= nodeNumber;
        sqrAvg /= nodeNumber;
        sqrAvg = Math.sqrt(sqrAvg);
        return avg + k * sqrAvg;
    }

}
