package img.threshold;

import util.ColorUtils;
import util.IntervalUtils;

import java.awt.image.BufferedImage;

/**
 * The type Adaptive thresholding.
 */
public class AdaptiveThresholding extends Thresholding {
    /**
     * The Alpha.
     */
    double alpha = (double) 2 / 3;

    private int k;

    /**
     * Instantiates a new Adaptive thresholding.
     *
     * @param bufferedImage the buffered image
     */
    protected AdaptiveThresholding(BufferedImage bufferedImage) {
        super(bufferedImage);
    }

    @Override
    public double getThreshold(int x, int y) {
        k = 1;
        double t;
        while (true) {
            var interval = getIntervalOfPoints(x, y);

            int maxF = 0;
            int minF = 255;
            double sum = 0;

            for (int i = interval.getXBase(); i <= interval.getXMax(); i++) {
                for (int j = interval.getYBase(); j <= interval.getYMax(); j++) {
                    int color = bufferedImage.getRGB(i, j);
                    int grayscalePixel = ColorUtils.getGrayscalePixel(color);
                    maxF = Math.max(maxF, grayscalePixel);
                    minF = Math.min(minF, grayscalePixel);
                    sum += grayscalePixel;
                }
            }

            double P = sum * ((double) 1 / Math.pow(2 * k + 1, 2));
            double diffFromMin = Math.abs(minF - P);
            double diffFromMax = Math.abs(maxF - P);

            if (diffFromMax > diffFromMin) {
                t = alpha * ((double) 2 / 3 * minF + (double) 1 / 3 * P);
            } else if (diffFromMax < diffFromMin) {
                t = alpha * ((double) 2 / 3 * P + (double) 1 / 3 * minF);
            } else {
                if (maxF != minF) {
                    k++;
                    continue;
                }
                t = alpha * P;
            }
            break;
        }

        return t;
    }

    @Override
    protected Interval getIntervalOfPoints(int x, int y) {
        var interval = new Interval();
        interval.setXBase(x - k);
        interval.setYBase(y - k);
        interval.setXMax(x + k);
        interval.setYMax(y + k);
        IntervalUtils.checkValuesOnIntervalAndSet(interval, bufferedImage);
        return interval;
    }
}
