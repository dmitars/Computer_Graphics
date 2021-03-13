package img.threshold;

import java.awt.image.BufferedImage;

/**
 * The type Thresholding.
 */
public abstract class Thresholding {
    /**
     * The Buffered image.
     */
    protected final BufferedImage bufferedImage;

    /**
     * Instantiates a new Thresholding.
     *
     * @param bufferedImage the buffered image
     */
    protected Thresholding(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }


    /**
     * Gets threshold.
     *
     * @param x the x
     * @param y the y
     * @return the threshold
     */
    abstract public double getThreshold(int x, int y);

    /**
     * Gets interval of points.
     *
     * @param x the x
     * @param y the y
     * @return the interval of points
     */
    protected abstract Interval getIntervalOfPoints(int x, int y);

}
