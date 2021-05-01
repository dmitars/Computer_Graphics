package loaders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.FXSystem;

import java.io.IOException;

/**
 * The type Loader.
 */
public abstract class Loader {

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

    FXSystem system;

    int minHeight;
    int minWidth;

    /**
     * Instantiates a new Loader.
     *
     * @param system the system
     */
    public Loader(FXSystem system){
        this.system = system;
        setPagePath();
        setTitle();
        setMinWidth();
        setMinHeight();
    }

    /**
     * Sets title.
     */
    abstract void setTitle();

    /**
     * Sets page path.
     */
    abstract void setPagePath();

    abstract void setMinHeight();

    abstract void setMinWidth();

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
        loader.setLocation(system.getPagePath(pagePath));
        return loader;
    }

    private void loadPage(FXMLLoader loader){
        try{
            Parent root = getRoot(loader);
            configureStage(root,title);
            showStage();
            setControllerParameters(loader);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private Parent getRoot(FXMLLoader loader) throws IOException {
        return loader.load();
    }

    private void configureStage(Parent root, String title){
        Stage stage = system.getStage();
        stage.setTitle(title);
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.setWidth(880);
        stage.setHeight(475);
        stage.setScene(new Scene(root));
    }

    private void showStage(){
        Stage stage = system.getStage();
        stage.show();
    }
}
