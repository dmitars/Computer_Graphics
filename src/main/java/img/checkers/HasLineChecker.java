package img.checkers;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * The type Has line checker.
 */
public class HasLineChecker extends Checker{
    private static final int[][] R1 = new int[][]{
            {-1, -1, -1},
            {2, 2, 2},
            {-1, -1, -1}
    };

    private static final int[][] R2 = new int[][]{
            {-1, 2, -1},
            {-1, 2, -1},
            {-1, 2, -1}
    };

    private static final int[][] R3 = new int[][]{
            {-1, -1, 2},
            {-1, 2, -1},
            {2, -1, -1}
    };

    private static final int[][] R4 = new int[][]{
            {2, -1, -1},
            {-1, 2, -1},
            {-1, -1, 2}
    };

    /**
     * Instantiates a new Has line checker.
     *
     * @param bufferedImage the buffered image
     */
    public HasLineChecker(BufferedImage bufferedImage) {
        super(bufferedImage);
    }

    @Override
    public boolean hasElement(int x, int y, double t) {
        double horR = getMaskResponse(x, y, R1);
        double vertR = getMaskResponse(x, y, R2);
        double diagPlus45R = getMaskResponse(x, y, R3);
        double diagMinus45R = getMaskResponse(x, y, R4);
        double[] results = new double[]{horR, vertR, diagPlus45R, diagMinus45R};
        var maxMask = Arrays.stream(results).max().orElse(0);
        return Math.abs(maxMask) > t;
    }
}
