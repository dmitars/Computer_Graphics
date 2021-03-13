package controllers;

import img.FileProcessor;
import img.ImageStatistic;
import img.threshold.ThresholdType;
import img.threshold.ThresholdingFactory;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import util.FileChooserUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 * The type Main controller.
 */
public class MainController extends Controller {

    @FXML
    private Label lFile;

    @FXML
    private Button btnProcess;

    @FXML
    private Button btnChooseFile;

    @FXML
    private ComboBox<ThresholdType> cbThresholding;

    @FXML
    private ImageView ivStart;

    @FXML
    private ImageView ivWithPoints;

    @FXML
    private ImageView ivWithLines;

    @FXML
    private ImageView ivWithBorders;

    @FXML
    private Label errorLabel;

    private File selectedFile;


    @Override
    public void init() {
        btnProcess.setOnAction(event -> processClicked());
        btnChooseFile.setOnAction(actionEvent -> selectFile());

        var items = FXCollections.observableArrayList(ThresholdType.values());
        cbThresholding.setItems(items);
        cbThresholding.getSelectionModel().selectFirst();
    }

    private void selectFile() {
        var fileChooser = FileChooserUtils.getImageFileChooser();
        //fileChooser.setInitialDirectory(new File("D:\\Computer Graphic\\lab3_full\\test"));
        var fileFromChooser = fileChooser.showOpenDialog(null);
        selectedFile = fileFromChooser == null ? selectedFile : fileFromChooser;
        setImageFromFile(selectedFile);
        lFile.setText(selectedFile.getAbsolutePath());
    }

    private void setImageFromFile(File file){
        try {
            ivStart.setImage(SwingFXUtils.toFXImage(ImageIO.read(file), null));
        } catch (IOException e) {
            showErrorMessage(e.getMessage());
        }
    }

    private void processClicked() {
        try {
            checkFile(selectedFile);
            startSetImgTask(selectedFile);
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
    }

    private void checkFile(File file) throws Exception {
        if (file == null)
            throw new Exception("File not selected");
    }

    private void startSetImgTask(File file) {
        var task = new Task<ImageStatistic>() {
            @Override
            protected ImageStatistic call() throws Exception {
                return setImgFromDir(file);
            }
        };

        task.setOnCancelled(workerStateEvent -> errorLabel.setText(""));
        errorLabel.setText("Processing...");

        new Thread(() -> {
            try {
                var data = task.call();
                ivWithPoints.setImage(SwingFXUtils.toFXImage(data.getImageWithPoints(), null));
                ivWithLines.setImage(SwingFXUtils.toFXImage(data.getImageWithLines(), null));
                ivWithBorders.setImage(SwingFXUtils.toFXImage(data.getImageWithBorders(), null));
                task.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private ImageStatistic setImgFromDir(File file) throws Exception {
        var start = Instant.now();
        var selectedThresholdType = cbThresholding.getSelectionModel().getSelectedItem();
        var result = FileProcessor.process(file, selectedThresholdType);
        var end = Instant.now();
        System.out.println("Time of processing: " + Duration.between(start, end).getSeconds() + " sec");
        return result;
    }

    @Override
    public void refresh() {

    }

    @Override
    public void close() {

    }
}
