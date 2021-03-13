package util;

import img.threshold.Interval;

import java.awt.image.BufferedImage;

/**
 * The type Interval utils.
 */
public class IntervalUtils {
    /**
     * Check values on interval and set.
     *
     * @param interval      the interval
     * @param bufferedImage the buffered image
     */
    public static void checkValuesOnIntervalAndSet(Interval interval, BufferedImage bufferedImage){
        interval.setXBase(Math.max(0,interval.getXBase()));
        interval.setYBase(Math.max(0, interval.getYBase()));
        interval.setXMax(Math.min(interval.getXMax(),bufferedImage.getWidth()-1));
        interval.setYMax(Math.min(bufferedImage.getHeight() - 1, interval.getYMax()));
    }
}
