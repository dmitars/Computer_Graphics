package img.checkers;

import java.awt.image.BufferedImage;

/**
 * The type Has point checker.
 */
public class HasPointChecker extends Checker{
    private static final int[][] w = new int[][]{
            {-1, -1, -1},
            {-1, 8, -1},
            {-1, -1, -1}
    };

    /**
     * Instantiates a new Has point checker.
     *
     * @param bufferedImage the buffered image
     */
    public HasPointChecker(BufferedImage bufferedImage) {
        super(bufferedImage);
    }

    @Override
    public boolean hasElement(int x, int y, double t) {
        double R = getMaskResponse(x, y, w);
        return Math.abs(R) > t;
    }
}
