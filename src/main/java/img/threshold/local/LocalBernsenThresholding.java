package img.threshold.local;

import util.ColorUtils;

import java.awt.image.BufferedImage;

/**
 * The type Local bernsen thresholding.
 */
public class LocalBernsenThresholding extends LocalThresholding {

    /**
     * Instantiates a new Local bernsen thresholding.
     *
     * @param bufferedImage the buffered image
     */
    public LocalBernsenThresholding(BufferedImage bufferedImage) {
        super(bufferedImage);
    }

    @Override
    public double getThreshold(int x, int y) {
        var interval = getIntervalOfPoints(x,y);

        double minBrightness = -255;
        double maxBrightness = 255;
        for (int i = interval.getXBase(); i <= interval.getXMax(); i++) {
            for (int j = interval.getYBase(); j <= interval.getYMax(); j++) {
                int color = bufferedImage.getRGB(i, j);
                double brightness = ColorUtils.getBrightness(color);
                minBrightness = Math.min(minBrightness, brightness);
                maxBrightness = Math.max(maxBrightness, brightness);
            }
        }

        return (minBrightness + maxBrightness) / 2;
    }
}
