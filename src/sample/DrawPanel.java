package sample;

import com.company.algorithms.Algorithm;
import com.company.algorithms.CyrusBeckAlgorithm;
import com.company.algorithms.LiangBarskyAlgorithm;
import com.company.utils.IntegerPairUtils;
import com.company.utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    private static final int CELL_STROKE_WIDTH = 1;
    private static final int COORD_LINES_STROKE_WIDTH = 3;
    private static final int RECT_STROKE_WIDTH = 5;
    private static final int LINE_WIDTH = 6;

    private int numCellsX = 20;
    private int numCellsY;
    private int cellSize = 10;
    private ArrayList<ArrayList<Integer>> lines = new ArrayList<>();
    private ArrayList<Integer> rect = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> polygon = new ArrayList<>();
    Algorithm algorithm = new LiangBarskyAlgorithm();
    CyrusBeckAlgorithm cyrusBeckAlgorithm = new CyrusBeckAlgorithm();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCoordinates(g);
        drawCoordinateRectangles(numCellsX, g);

        drawLines(g, lines, Color.BLACK, Color.RED);
        drawPolygonLines(g, lines, polygon, Color.GRAY, Color.BLUE);

        if (!rect.isEmpty()) {
            drawRectangle(rect, cellSize, g);
        }
    }

    private void drawPolygonLines(Graphics g,
                                  ArrayList<ArrayList<Integer>> lines,
                                  ArrayList<ArrayList<Integer>> polygon,
                                  Color baseColor,
                                  Color markColor) {
        for (var line : polygon) {
            drawLine(line.get(0), line.get(1), line.get(2), line.get(3), cellSize, g, baseColor);
        }

        for (ArrayList<Integer> line : lines) {
            if (!rect.isEmpty() && !line.isEmpty()) {
                ArrayList<Integer> l = cyrusBeckAlgorithm.calculate(polygon, line);
                if (!l.isEmpty()) {
                    drawLine(l.get(0), l.get(1), l.get(2), l.get(3), cellSize, g, markColor);
                }
            }
        }
    }

    private void drawLines(Graphics g, ArrayList<ArrayList<Integer>> lines, Color baseColor, Color markColor) {
        for (ArrayList<Integer> line : lines) {
            drawLine(line.get(0), line.get(1), line.get(2), line.get(3), cellSize, g, baseColor);

            if (!rect.isEmpty()) {
                ArrayList<Integer> l = algorithm.calc(line, rect);
                if (!l.isEmpty()) {
                    drawLine(l.get(0), l.get(1), l.get(2), l.get(3), cellSize, g, markColor);
                }
            }
        }
    }

    private void drawCoordinates(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle bounds = getBounds();
        Line2D yLine = new Line2D.Double((int) (bounds.width / 2), 0, (int) (bounds.width / 2), bounds.height);
        Line2D xLine = new Line2D.Double(0, (int) (bounds.height / 2), bounds.width, (int) (bounds.height / 2));
        g2.setColor(Color.LIGHT_GRAY);
        g2.setStroke(new BasicStroke(COORD_LINES_STROKE_WIDTH));
        g2.draw(xLine);
        g2.draw(yLine);
    }

    private void drawCoordinateRectangles(int n, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle bounds = getBounds();
        cellSize = (Math.min(bounds.width, bounds.height)) / n;
        int nX = bounds.width / cellSize;
        int nY = bounds.height / cellSize;
        numCellsY = nY * 2;
        g2.setStroke(new BasicStroke(CELL_STROKE_WIDTH));
        for (int i = -nX / 2; i < nX / 2; i++) {
            Line2D line = new Line2D.Double((int) (bounds.width / 2) + cellSize * i, 0,
                    (int) (bounds.width / 2) + cellSize * i, bounds.height);
            g2.draw(line);
        }
        for (int i = -nY / 2; i < nY / 2; i++) {
            Line2D line = new Line2D.Double(0, (int) (bounds.height / 2) + cellSize * i, bounds.width,
                    (int) (bounds.height / 2) + cellSize * i);
            g2.draw(line);
        }
    }

    private void drawLine(int x1, int y1, int x2, int y2, int cellSize, Graphics g, Color c) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(LINE_WIDTH));
        Rectangle bounds = getBounds();
        g2.setColor(c);
        g2.drawLine((bounds.width / 2) + x1 * cellSize, (bounds.height / 2) - cellSize * y1,
                (bounds.width / 2) + x2 * cellSize, (bounds.height / 2) - cellSize * y2);
    }

    private void drawRectangle(ArrayList<Integer> rect, int cellSize, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(RECT_STROKE_WIDTH));
        Rectangle bounds = getBounds();
        g2.setColor(Color.RED);

        int x1 = rect.get(0);
        int x2 = rect.get(2);
        int y1 = rect.get(1);
        int y2 = rect.get(3);

        g2.drawLine((bounds.width / 2) + x1 * cellSize, (bounds.height / 2) - cellSize * y1,
                (bounds.width / 2) + x2 * cellSize, (bounds.height / 2) - cellSize * y1);
        g2.drawLine((bounds.width / 2) + x1 * cellSize, (bounds.height / 2) - cellSize * y2,
                (bounds.width / 2) + x2 * cellSize, (bounds.height / 2) - cellSize * y2);
        g2.drawLine((bounds.width / 2) + x1 * cellSize, (bounds.height / 2) - cellSize * y1,
                (bounds.width / 2) + x1 * cellSize, (bounds.height / 2) - cellSize * y2);
        g2.drawLine((bounds.width / 2) + x2 * cellSize, (bounds.height / 2) - cellSize * y1,
                (bounds.width / 2) + x2 * cellSize, (bounds.height / 2) - cellSize * y2);
    }

    public void drawRectangle(ArrayList<Integer> rect) {
        this.rect = rect;
        if (!rect.isEmpty()) {
            var temp = new ArrayList<ArrayList<Integer>>() {{
                add(rect);
            }};
            updateCellsWithLines(temp);
            return;
        }
        repaint();
    }

    public void updateLines(ArrayList<ArrayList<Integer>> lines) {
        this.lines = lines;
        updateCellsWithLines(lines);
    }

    public void updatePolygon(ArrayList<ArrayList<Integer>> polygon) {
        this.polygon = polygon;
        updateCellsWithLines(polygon);
    }

    private void updateCellsWithLines(ArrayList<ArrayList<Integer>> lines) {
        Pair<Integer, Integer> deltas = getMaxDxDyFromLines(lines);
        repaintCellsWith(deltas);
        repaint();
    }

    private void repaintCellsWith(Pair<Integer, Integer> deltas) {
        if (numCellsX / 2 < deltas.getFirst() || numCellsX / 2 + 6 > deltas.getFirst())
            numCellsX = deltas.getFirst() * 2 + 6;
        if (numCellsY / 2 < deltas.getSecond())
            numCellsX = deltas.getSecond() * 2 + 2;
    }

    private Pair<Integer, Integer> getMaxDxDyFromLines(ArrayList<ArrayList<Integer>> lines) {
        var maxPair = new Pair<>(0, 0);
        for (ArrayList<Integer> line : lines) {
            var tempPair = getMaxDxDyFromLine(line);
            maxPair = IntegerPairUtils.getPairWithMaxValuesOf(maxPair, tempPair);
        }
        return maxPair;
    }

    private Pair<Integer, Integer> getMaxDxDyFromLine(ArrayList<Integer> line) {
        int dx = Math.max(Math.abs(line.get(0)), Math.abs(line.get(2)));
        int dy = Math.max(Math.abs(line.get(1)), Math.abs(line.get(3)));

        return new Pair<>(dx, dy);
    }
}
