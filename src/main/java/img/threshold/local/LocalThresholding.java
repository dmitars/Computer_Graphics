package img.threshold.local;

import img.threshold.Interval;
import util.IntervalUtils;
import img.threshold.Thresholding;

import java.awt.image.BufferedImage;

/**
 * The type Local thresholding.
 */
public abstract class LocalThresholding extends Thresholding {
    private static final int r = 15;

    /**
     * Instantiates a new Local thresholding.
     *
     * @param bufferedImage the buffered image
     */
    public LocalThresholding(BufferedImage bufferedImage) {
        super(bufferedImage);
    }

    protected Interval getIntervalOfPoints(int x, int y){
        var interval = new Interval();
        interval.setXBase(x - r / 2);
        interval.setYBase(y - r / 2);
        interval.setXMax(x + r / 2);
        interval.setYMax(y + r / 2);
        IntervalUtils.checkValuesOnIntervalAndSet(interval, bufferedImage);
        return interval;
    }
}
