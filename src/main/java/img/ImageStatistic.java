package img;

import java.awt.image.BufferedImage;

/**
 * The type Image statistic.
 */
public class ImageStatistic {
    /**
     * The Image with points.
     */
    BufferedImage imageWithPoints;
    /**
     * The Image with lines.
     */
    BufferedImage imageWithLines;
    /**
     * The Image with borders.
     */
    BufferedImage imageWithBorders;

    /**
     * Gets image with points.
     *
     * @return the image with points
     */
    public BufferedImage getImageWithPoints() {
        return imageWithPoints;
    }

    /**
     * Sets image with points.
     *
     * @param imageWithPoints the image with points
     */
    public void setImageWithPoints(BufferedImage imageWithPoints) {
        this.imageWithPoints = imageWithPoints;
    }

    /**
     * Gets image with lines.
     *
     * @return the image with lines
     */
    public BufferedImage getImageWithLines() {
        return imageWithLines;
    }

    /**
     * Sets image with lines.
     *
     * @param imageWithLines the image with lines
     */
    public void setImageWithLines(BufferedImage imageWithLines) {
        this.imageWithLines = imageWithLines;
    }

    /**
     * Gets image with borders.
     *
     * @return the image with borders
     */
    public BufferedImage getImageWithBorders() {
        return imageWithBorders;
    }

    /**
     * Sets image with borders.
     *
     * @param imageWithBorders the image with borders
     */
    public void setImageWithBorders(BufferedImage imageWithBorders) {
        this.imageWithBorders = imageWithBorders;
    }
}
