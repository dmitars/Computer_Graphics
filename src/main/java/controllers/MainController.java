package controllers;

import javafx.collections.ObservableFloatArray;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.*;
import letter.LetterMesh;
import letter.LetterPresenter;
import letter.LetterView;
import utils.ControlUtils;
import utils.MeshViewUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller implements LetterView {
    private static final int CANVAS_SIZE = 100;
    private static final int LETTER_SIZE = 300;

    private MeshView meshView;
    private LetterMesh letterMesh;

    @FXML
    private GridPane gridPane;
    private Group mainGroup;
    private TextArea transformationMatrixTextArea;
    private Canvas xyProjection;
    private Canvas yzProjection;
    private Canvas xzProjection;

    private LetterPresenter presenter;

    private final List<Rotate> initialRotations = new ArrayList<>() {{
        add(new Rotate(-15, 0, 0, 0, Rotate.Y_AXIS));
        add(new Rotate(-15, 0, 0, 0, Rotate.X_AXIS));
    }};

    public void init() {
        this.presenter = new LetterPresenter(this);

        this.gridPane.setHgap(20);

        this.setupColumns();
        this.setupRows();

        this.mainGroup = new Group();
        this.mainGroup.getTransforms().addAll(this.initialRotations);
        this.gridPane.add(this.mainGroup, 0, 3);

        this.transformationMatrixTextArea = ControlUtils.createReadonlyTextArea(120, 100);
        this.xyProjection = ControlUtils.createCanvas(CANVAS_SIZE, CANVAS_SIZE);
        this.yzProjection = ControlUtils.createCanvas(CANVAS_SIZE, CANVAS_SIZE);
        this.xzProjection = ControlUtils.createCanvas(CANVAS_SIZE, CANVAS_SIZE);

        this.gridPane.add(this.transformationMatrixTextArea, 1, 3);
        this.gridPane.add(this.xyProjection, 2, 1);
        this.gridPane.add(this.xzProjection, 2, 3);
        this.gridPane.add(this.yzProjection, 2, 5);


        var xyTextArea = ControlUtils.createReadonlyTextField(50, 8);
        xyTextArea.setText("OXY");
        gridPane.add(xyTextArea, 2, 0);

        var yzTextArea = ControlUtils.createReadonlyTextField(50, 8);
        yzTextArea.setText("OYZ");
        gridPane.add(yzTextArea, 2, 2);

        var xzTextArea = ControlUtils.createReadonlyTextField(50, 8);
        xzTextArea.setText("OXZ");
        gridPane.add(xzTextArea, 2, 4);

        this.addLetterMeshViewGroup();
        this.addAxisGroup();

        this.setupKeyEventHandlers();
        this.updateProjections();
    }

    @Override
    public void refresh() {

    }

    @Override
    public void close() {

    }

    private void setupColumns() {
        this.gridPane.getColumnConstraints().clear();
        this.gridPane.getColumnConstraints().add(ControlUtils.createColumn(80, HPos.CENTER));
        this.gridPane.getColumnConstraints().add(ControlUtils.createColumn(20, HPos.RIGHT));
        this.gridPane.getColumnConstraints().add(ControlUtils.createColumn(40, HPos.CENTER));
    }

    private void setupRows() {
        this.gridPane.getRowConstraints().clear();
        this.gridPane.getRowConstraints().add(ControlUtils.createRow(10, VPos.CENTER));
        this.gridPane.getRowConstraints().add(ControlUtils.createRow(80, VPos.CENTER));
        this.gridPane.getRowConstraints().add(ControlUtils.createRow(10, VPos.CENTER));
        this.gridPane.getRowConstraints().add(ControlUtils.createRow(80, VPos.CENTER));
        this.gridPane.getRowConstraints().add(ControlUtils.createRow(10, VPos.CENTER));
        this.gridPane.getRowConstraints().add(ControlUtils.createRow(80, VPos.CENTER));
    }

    private void addLetterMeshViewGroup() {
        letterMesh = new LetterMesh(100, 130);
        meshView = MeshViewUtils.createMeshView(letterMesh.getMesh());
        this.mainGroup.getChildren().add(meshView);
    }

    private void addAxisGroup() {
        Group axis = new Group();

        int width = LETTER_SIZE;

        Box xAxis = ControlUtils.createBox(width, 3, 3);
        xAxis.setTranslateY(width / 2);
        xAxis.setTranslateZ(width / 2);

        Box yAxis = ControlUtils.createBox(3, width, 3);
        yAxis.setTranslateX(-width / 2);
        yAxis.setTranslateZ(width / 2);

        Box zAxis = ControlUtils.createBox(3, 3, width);
        zAxis.setTranslateX(-width / 2);
        zAxis.setTranslateY(width / 2);

        axis.getChildren().addAll(xAxis, yAxis, zAxis);
        axis.getTransforms().addAll(this.initialRotations);

        this.gridPane.add(axis, 0, 3);
    }

    private void setupKeyEventHandlers() {
        this.gridPane.requestFocus();
        this.gridPane.addEventHandler(KeyEvent.KEY_PRESSED, this.presenter::onKeyPressed);
    }

    @Override
    public void rotateLetter(Rotate rotate) {
        this.transform(rotate);
    }

    @Override
    public void scaleLetter(Scale scale) {
        this.transform(scale);
    }

    @Override
    public void translateXLetter(Translate translate) {
        this.transform(translate);
    }

    private void transform(Transform transform) {
        this.mainGroup.getTransforms().add(transform);
        this.showTransformationMatrix(transform);
        updateProjections();
    }

    private void updateProjections() {
        this.updateProjection(xyProjection, 1);
        this.updateProjection(xzProjection, 2);
        this.updateProjection(yzProjection, 0);
    }

    private void updateProjection(Canvas projection, int notInUsePointIndex) {
        projection.getGraphicsContext2D().clearRect(0, 0, CANVAS_SIZE, CANVAS_SIZE);
        var gc = projection.getGraphicsContext2D();
        gc.setFill(Color.AQUA);
        gc.fill();
        var points = letterMesh.getMesh().getPoints();
        var neighbours = LetterMesh.getNeighbours();
        for (var vertex : neighbours.keySet()) {
            var vertexNeighbours = neighbours.get(vertex);
            var currPoint = get3dPointOf(points, vertex);
            vertexNeighbours.forEach(neighbour -> {
                var point = get3dPointOf(points, neighbour);
                drawLineBetween(currPoint, point, notInUsePointIndex, projection.getGraphicsContext2D());
            });
        }
    }

    private Point3D get3dPointOf(ObservableFloatArray points, int index) {
        return mainGroup.localToParent(
                new Point3D(points.get(index * 3), points.get(index * 3 + 1), points.get(index * 3 + 2)));
    }

    private void drawLineBetween(Point3D first, Point3D second, int notInUsePointIndex, GraphicsContext graphicsContext) {
        var first2dPoint = get2dFrom3D(first, notInUsePointIndex);
        var second2dPoint = get2dFrom3D(second, notInUsePointIndex);
        graphicsContext.strokeLine(first2dPoint.getX(), first2dPoint.getY(), second2dPoint.getX(), second2dPoint.getY());
    }

    private Point2D get2dFrom3D(Point3D point3D, int indexWithout) {
        double[] arr = new double[2];
        boolean wasModified = false;
        for (int j = 0; j < 3; j++) {
            if (j == indexWithout)
                continue;
            double value = switch (j) {
                case 0:
                    yield point3D.getX();
                case 1:
                    yield point3D.getY();
                case 2:
                    yield point3D.getZ();
                default:
                    yield 0;
            };
            if (!wasModified) {
                wasModified = true;
                arr[0] = value;
            } else
                arr[1] = value;
        }
        return new Point2D(arr[0] * CANVAS_SIZE / LETTER_SIZE+10, arr[1] * CANVAS_SIZE / LETTER_SIZE+10);
    }

    private void showTransformationMatrix(Transform transform) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        StringBuilder matrix = new StringBuilder();

        matrix
                .append(transform.getClass().getSimpleName())
                .append(": \n");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                matrix
                        .append(decimalFormat.format(transform.getElement(MatrixType.MT_3D_3x4, i, j)))
                        .append(" ");
            }
            matrix.append("\n");
        }

        this.transformationMatrixTextArea.setText(matrix.toString());
    }
}