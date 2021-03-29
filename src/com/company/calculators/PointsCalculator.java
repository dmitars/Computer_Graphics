package com.company.calculators;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

/**
 * The type Points calculator.
 */
public abstract class PointsCalculator {
    private long countedTimeNanos = 0;

    /**
     * Calculate array list.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     * @return the array list
     */
    protected abstract ArrayList<Point> calculate(int x1, int y1, int x2, int y2);

    /**
     * Calculate with time counting array list.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     * @return the array list
     */
    public ArrayList<Point>calculateWithTimeCounting(int x1, int y1, int x2, int y2){
        var start = Instant.now();
        var points = calculate(x1, y1, x2, y2);
        var end = Instant.now();
        countedTimeNanos = Duration.between(end,start).toNanos();
        return points;
    }

    /**
     * Set additional parameter.
     *
     * @param parameter the parameter
     */
    public void setAdditionalParameter(int parameter){}

    /**
     * Gets counted time.
     *
     * @return the counted time
     */
    public long getCountedTime() {
        return countedTimeNanos;
    }
}
