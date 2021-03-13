package img;

import img.checkers.Checker;
import img.checkers.CheckerFactory;
import img.checkers.CheckerType;
import img.threshold.ThresholdType;
import img.threshold.Thresholding;
import img.threshold.ThresholdingFactory;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * The type Segmentation checker.
 */
public class SegmentationChecker {
    private static final int THREADS_NUMBER = 8;

    private final BufferedImage bufferedImage;


    private Thresholding thresholding;
    private Checker checker;
    private BufferedImage result;

    /**
     * Instantiates a new Segmentation checker.
     *
     * @param bufferedImage the buffered image
     */
    public SegmentationChecker(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }


    /**
     * Gets checked image.
     *
     * @param thresholdType the threshold type
     * @param checkerType   the checker type
     * @return the checked image
     */
    public BufferedImage getCheckedImage(ThresholdType thresholdType, CheckerType checkerType) {
        result = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        checker = CheckerFactory.getCheckerWithType(checkerType, bufferedImage);
        thresholding = ThresholdingFactory.getThresholding(thresholdType, bufferedImage);

        var threadsList = getThreadList();
        executeThreads(threadsList);
        return result;
    }

    private ArrayList<MyThread> getThreadList() {
        int linesNumber = bufferedImage.getWidth() / THREADS_NUMBER + 1;
        var threadsList = new ArrayList<MyThread>();
        for (int i = 0; i < THREADS_NUMBER; i++) {
            threadsList.add(new MyThread(i * linesNumber, linesNumber));
        }
        return threadsList;
    }

    private void executeThreads(ArrayList<MyThread> threads) {
        try {
            var service = Executors.newFixedThreadPool(THREADS_NUMBER);
            service.invokeAll(threads);
            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * The type My thread.
     */
    class MyThread implements Callable<Void> {
        /**
         * The Start index.
         */
        int startIndex;
        /**
         * The Lines number.
         */
        int linesNumber;

        /**
         * Instantiates a new My thread.
         *
         * @param startIndex  the start index
         * @param linesNumber the lines number
         */
        public MyThread(int startIndex, int linesNumber) {
            this.startIndex = startIndex;
            this.linesNumber = linesNumber;
        }

        @Override
        public Void call() {
            int endIndex = Math.min(bufferedImage.getWidth(), startIndex + linesNumber);
            for (int i = startIndex; i < endIndex; i++) {
                for (int j = 0; j < bufferedImage.getHeight(); j++) {
                    var t = thresholding.getThreshold(i, j);
                    if (checker.hasElement(i, j, t))
                        result.setRGB(i, j, 255);
                    else
                        result.setRGB(i, j, 0);
                }
            }
            return null;
        }
    }
}
