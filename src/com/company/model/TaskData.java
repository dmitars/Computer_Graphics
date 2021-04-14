package com.company.model;

import java.util.ArrayList;

public class TaskData {
    private ArrayList<Integer> rect;
    private ArrayList<ArrayList<Integer>> lines;
    private ArrayList<ArrayList<Integer>> polygon;


    public TaskData(ArrayList<Integer> rect, ArrayList<ArrayList<Integer>> lines, ArrayList<ArrayList<Integer>> polygon) {
        this.rect = rect;
        this.lines = lines;
        this.polygon = polygon;
    }

    public ArrayList<ArrayList<Integer>> getPolygon() {
        return polygon;
    }

    public void setPolygon(ArrayList<ArrayList<Integer>> polygon) {
        this.polygon = polygon;
    }

    public ArrayList<Integer> getRect() {
        return rect;
    }

    public void setRect(ArrayList<Integer> rect) {
        this.rect = rect;
    }

    public ArrayList<ArrayList<Integer>> getLines() {
        return lines;
    }

    public void setLines(ArrayList<ArrayList<Integer>> lines) {
        this.lines = lines;
    }
}
