package img;

import img.checkers.CheckerType;
import img.threshold.ThresholdType;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * The type File processor.
 */
public class FileProcessor {

    /**
     * Process image statistic.
     *
     * @param file          the file
     * @param thresholdType the threshold type
     * @return the image statistic
     * @throws IOException the io exception
     */
    public static ImageStatistic process(File file, ThresholdType thresholdType) throws IOException {
        var image = ImageIO.read(file);
        var segmentationChecker = new SegmentationChecker(image);
        var imageWithPoints = segmentationChecker.getCheckedImage(thresholdType, CheckerType.HAS_POINTS);
        var imageWithLines = segmentationChecker.getCheckedImage(thresholdType, CheckerType.HAS_LINES);
        var imageWithBorders = segmentationChecker.getCheckedImage(thresholdType, CheckerType.HAS_BORDERS);
        var imageStatistic = new ImageStatistic();
        imageStatistic.setImageWithPoints(imageWithPoints);
        imageStatistic.setImageWithLines(imageWithLines);
        imageStatistic.setImageWithBorders(imageWithBorders);
        return imageStatistic;
    }
}
