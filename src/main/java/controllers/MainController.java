package controllers;

import images.DirProcessor;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import model.ImgStatistic;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

/**
 * The type Main controller.
 */
public class MainController extends Controller {

    @FXML
    private Label titleLabel;

    @FXML
    private Label errorLabel;

    @FXML
    private Label lDirectory;

    @FXML
    private Button btnChooseDir;

    @FXML
    private TableView<ImgStatistic> resultTable;


    /**
     * The Data list.
     */
    ObservableList<ImgStatistic> dataList;

    @Override
    public void init() {
        btnChooseDir.setOnAction(actionEvent -> selectDir());

        resultTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        dataList = FXCollections.observableArrayList(new ArrayList<>());

        var identifiers = ImgStatistic.getIdentifiers();
        var columnTitles = ImgStatistic.getGUIIdentifiers();
        for (int i = 0; i < identifiers.length; i++) {
            var column = new TableColumn<ImgStatistic, String>();
            column.setText(columnTitles[i]);
            column.setCellValueFactory(new PropertyValueFactory<>(identifiers[i]));
            resultTable.getColumns().add(column);
        }

        resultTable.setItems(dataList);

    }


    private void selectDir() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select folder with images");
        var selectedDir = directoryChooser.showDialog(system.getStage());
        if (selectedDir == null)
            return;
        lDirectory.setText(selectedDir.getAbsolutePath());
        errorLabel.setText("Processing...");
        startSetImgTask(selectedDir);
    }

    private void startSetImgTask(File selectedDir){
        var task = new Task<ArrayList<ImgStatistic>>() {
            @Override
            protected ArrayList<ImgStatistic> call() throws Exception {
                return setImgFromDir(selectedDir);
            }
        };

        task.setOnCancelled(workerStateEvent -> errorLabel.setText(""));

        new Thread(()->{
            try {
                var data = task.call();
                dataList.addAll(data);
                task.cancel();
            } catch (Exception e) {
                errorLabel.setText(e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }

    private ArrayList<ImgStatistic> setImgFromDir(File selectedDir) throws Exception {
        var start = Instant.now();
        DirProcessor processor = new DirProcessor(selectedDir);
        resultTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        var data = processor.getImagesData();
        var end = Instant.now();
        System.out.println("Time of processing: " + Duration.between(start, end).getSeconds() + " sec");
        return data;
    }

    @Override
    public void refresh() {

    }

    @Override
    public void close() {

    }
}
