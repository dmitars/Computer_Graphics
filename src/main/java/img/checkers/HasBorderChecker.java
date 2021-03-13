package img.checkers;

import java.awt.image.BufferedImage;

/**
 * The type Has border checker.
 */
public class HasBorderChecker extends Checker{
    private static final int[][] Gy = new int[][]{
            {-1, 0, 1},
            {-2, 0, 2},
            {-1, 0, 1}
    };

    private static final int[][] Gx = new int[][]{
            {-1, -2, -1},
            {0, 0, 0},
            {1, 2, 1}
    };

    /**
     * Instantiates a new Has border checker.
     *
     * @param bufferedImage the buffered image
     */
    public HasBorderChecker(BufferedImage bufferedImage) {
        super(bufferedImage);
    }

    @Override
    public boolean hasElement(int x, int y, double t) {
        double maskValueGx = getMaskResponse(x, y, Gx);
        double maskValueGy = getMaskResponse(x, y, Gy);
        var gradient = Math.sqrt(Math.pow(maskValueGx, 2) + Math.pow(maskValueGy, 2));
        return Math.abs(gradient) > t;
    }
}
