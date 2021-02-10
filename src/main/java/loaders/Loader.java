package loaders;

import controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.MainSystem;

import java.io.IOException;

/**
 * The type Loader.
 */
public abstract class Loader {
    private final int WINDOW_WIDTH = 700;
    private final int WINDOW_HEIGHT = 500;

    /**
     * The Page path.
     */
    String pagePath = null;
    /**
     * The Title.
     */
    String title = null;
    /**
     * The System.
     */
    MainSystem system;

    /**
     * Instantiates a new Loader.
     *
     * @param system the system
     */
    public Loader(MainSystem system){
        this.system = system;
        setPagePath();
        setTitle();
    }

    /**
     * Sets title.
     */
    abstract void setTitle();

    /**
     * Sets page path.
     */
    abstract void setPagePath();

    /**
     * Load.
     */
    public void load(){
        FXMLLoader loader = getLoader();
        loadPage(loader);
    }

    /**
     * Sets controller parameters.
     *
     * @param loader the loader
     */
    abstract void setControllerParameters(FXMLLoader loader);

    private FXMLLoader getLoader(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainSystem.class.getResource(pagePath));
        return loader;
    }

    private void loadPage(FXMLLoader loader){
        try{
            Parent root = getRoot(loader);
            setControllerParameters(loader);
            setPageControlSystem(loader);
            configureStage(root,title);
            showStage();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private Parent getRoot(FXMLLoader loader) throws IOException {
        return loader.load();
    }

    private void setPageControlSystem(FXMLLoader loader){
        Controller controller = loader.getController();
        controller.setControlSystem(system);
    }

    private void configureStage(Parent root, String title){
        Stage stage = system.getStage();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
    }

    private void showStage(){
        Stage stage = system.getStage();
        stage.show();
    }
}
