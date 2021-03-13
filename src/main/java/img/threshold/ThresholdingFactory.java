package img.threshold;

import img.threshold.local.LocalBernsenThresholding;
import img.threshold.local.LocalNiblatskThresholding;

import java.awt.image.BufferedImage;

/**
 * The type Thresholding factory.
 */
public class ThresholdingFactory {

    /**
     * Get thresholding thresholding.
     *
     * @param thresholdType the threshold type
     * @param bufferedImage the buffered image
     * @return the thresholding
     */
    public static Thresholding getThresholding(ThresholdType thresholdType, BufferedImage bufferedImage){
        return switch (thresholdType){
            case LOCAL_BERNSEN -> new LocalBernsenThresholding(bufferedImage);
            case LOCAL_NIBLATSK -> new LocalNiblatskThresholding(bufferedImage);
            case ADAPTIVE -> new AdaptiveThresholding(bufferedImage);
        };
    }
}
