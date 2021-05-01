package utils;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;


public class ControlUtils {

    private ControlUtils() {}

    public static ColumnConstraints createColumn(double width, HPos alignment) {
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(width);
        column.setHalignment(alignment);
        return column;
    }

    public static RowConstraints createRow(double height, VPos alignment) {
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(height);
        row.setValignment(alignment);
        return row;
    }

    public static TextArea createReadonlyTextArea(double width, double height) {
        TextArea textArea = new TextArea();
        textArea.setDisable(true);
        textArea.setMaxSize(width, height);
        return textArea;
    }

    public static TextField createReadonlyTextField(double width, double height) {
        TextField textField = new TextField();
        textField.setDisable(true);
        textField.setMaxSize(width, height);
        return textField;
    }

    public static Canvas createCanvas(double width, double height){
        Canvas canvas = new Canvas();
        canvas.setDisable(true);
        canvas.setWidth(width);
        canvas.setHeight(height);
        return canvas;
    }

    public static Box createBox(double w, double h, double d) {
        Box b = new Box(w, h, d);
        b.setMaterial(new PhongMaterial(Color.RED));
        return b;
    }

}
