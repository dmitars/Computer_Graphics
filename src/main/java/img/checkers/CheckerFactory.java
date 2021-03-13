package img.checkers;

import java.awt.image.BufferedImage;

/**
 * The type Checker factory.
 */
public class CheckerFactory {
    /**
     * Get checker with type checker.
     *
     * @param checkerType   the checker type
     * @param bufferedImage the buffered image
     * @return the checker
     */
    public static Checker getCheckerWithType(CheckerType checkerType, BufferedImage bufferedImage){
        return switch (checkerType){
            case HAS_LINES -> new HasLineChecker(bufferedImage);
            case HAS_POINTS -> new HasPointChecker(bufferedImage);
            case HAS_BORDERS -> new HasBorderChecker(bufferedImage);
        };
    }
}
