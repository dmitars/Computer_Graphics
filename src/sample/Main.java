package sample;


import com.company.reader.InputReader;

import java.util.ArrayList;

public class Main {
    private static final String path = "input.txt";

    static ArrayList<ArrayList<Integer>> lines = new ArrayList<>();
    static ArrayList<Integer> rectangle = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> polygon = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        var taskData = InputReader.readTaskDataFromPath(path);
        lines = taskData.getLines();
        rectangle = taskData.getRect();
        polygon = taskData.getPolygon();

        MainWindow mainWindow = new MainWindow();
        mainWindow.repaint();
    }
}
