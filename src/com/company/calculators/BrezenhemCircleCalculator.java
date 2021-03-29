package com.company.calculators;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Brezenhem circle calculator.
 */
public class BrezenhemCircleCalculator extends PointsCalculator {
    private int r;

    @Override
    public void setAdditionalParameter(int parameter) {
        r = parameter;
    }

    @Override
    public ArrayList<Point> calculate(int x1, int y1, int x2, int y2) {
        ArrayList<Point> points = new ArrayList<>();

        int x = 0;
        int y = r;
        int e = 3 - 2 * r;

        fillWithCombinations(points, x, y, x1, y1);

        while (x < y) {
            if (e >= 0) {
                e = e + 4 * (x - y) + 10;
                x = x + 1;
                y = y - 1;
            } else {
                e = e + 4 * x + 6;
                x = x + 1;
            }

            fillWithCombinations(points, x, y, x1, y1);
        }

        return points;
    }

    private void fillWithCombinations(List<Point> points, int x, int y, int x1, int y1) {
        points.add(new Point(x + x1, y + y1));
        points.add(new Point(x + x1, -y + y1));
        points.add(new Point(-x + x1, y + y1));
        points.add(new Point(-x + x1, -y + y1));

        points.add(new Point(y + x1, x + y1));
        points.add(new Point(-y + x1, x + y1));
        points.add(new Point(y + x1, -x + y1));
        points.add(new Point(-y + x1, -x + y1));
    }
}
