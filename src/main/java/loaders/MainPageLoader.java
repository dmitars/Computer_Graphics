package loaders;

import controllers.Controller;
import javafx.fxml.FXMLLoader;
import sample.FXSystem;

/**
 * The type Main page loader.
 */
public class MainPageLoader extends Loader {
    /**
     * Instantiates a new Main page loader.
     *
     * @param system the system
     */
    public MainPageLoader(FXSystem system) {
        super(system);
    }

    @Override
    void setTitle() {
        title = "Lab 3";
    }

    @Override
    void setPagePath() {
        pagePath = "sample.fxml";
    }

    @Override
    void setMinHeight() {
        minHeight = 400;
    }

    @Override
    void setMinWidth() {
        minWidth = 600;
    }

    @Override
    void setControllerParameters(FXMLLoader loader) {
        ((Controller)loader.getController()).init();
    }
}
