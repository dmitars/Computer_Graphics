package com.company.calculators;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public abstract class PointsCalculator {
    private long countedTimeNanos = 0;

    protected abstract ArrayList<Point> calculate(int x1, int y1, int x2, int y2);

    public ArrayList<Point>calculateWithTimeCounting(int x1, int y1, int x2, int y2){
        var start = Instant.now();
        var points = calculate(x1, y1, x2, y2);
        var end = Instant.now();
        countedTimeNanos = Duration.between(end,start).toNanos();
        return points;
    }

    public void setAdditionalParameter(int parameter){}

    public long getCountedTime() {
        return countedTimeNanos;
    }
}
