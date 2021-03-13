package img.checkers;

import util.ColorUtils;

import java.awt.image.BufferedImage;

/**
 * The type Checker.
 */
public abstract class Checker {
    private final BufferedImage bufferedImage;

    /**
     * Instantiates a new Checker.
     *
     * @param bufferedImage the buffered image
     */
    public Checker(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    /**
     * Gets mask response.
     *
     * @param x    the x
     * @param y    the y
     * @param mask the mask
     * @return the mask response
     */
    protected double getMaskResponse(int x, int y, int[][] mask) {
        double R = 0;
        int color = 0;
        for (int i = -1; i < 2; i++) {
            if (x + i < 0 || x + i >= bufferedImage.getWidth())
                continue;
            for (int j = -1; j < 2; j++) {
                if (y + j < 0 || y + j >= bufferedImage.getHeight())
                    continue;
                try {
                    color = bufferedImage.getRGB(x + i, y + j);
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
                R += ColorUtils.getGrayscalePixel(color) * mask[i + 1][j + 1];
            }
        }
        return R;
    }

    /**
     * Has element boolean.
     *
     * @param x the x
     * @param y the y
     * @param t the t
     * @return the boolean
     */
    public abstract boolean hasElement(int x, int y, double t);
}
