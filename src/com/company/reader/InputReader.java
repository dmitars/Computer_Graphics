package com.company.reader;


import com.company.model.TaskData;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class InputReader {
    private static Scanner scanner;

    public static com.company.model.TaskData readTaskDataFromPath(String pathValue) throws Exception {
        Path path = Paths.get(pathValue);
        scanner = new Scanner(path);
        var lines = readLines();
        var polygons = readLines();
        var rect = readLine();
        return new TaskData(rect,lines,polygons);
    }

    public static ArrayList<ArrayList<Integer>> readLines(){
        var linesNumber = scanner.nextInt();
        scanner.nextLine();
        return readLinesWithNumber(linesNumber);
    }

    public static ArrayList<ArrayList<Integer>> readLinesWithNumber(int numberOfLines){
        var result = new ArrayList<ArrayList<Integer>>();
        for(int i = 0;i<numberOfLines;i++){
            var temp = readLine();
            result.add(temp);
        }
        return result;
    }

    private static ArrayList<Integer>readLine(){
        var temp = new ArrayList<Integer>();
        for(int j = 0;j<4;j++)
            temp.add(scanner.nextInt());
        return temp;
    }
}
