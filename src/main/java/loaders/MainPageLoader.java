package loaders;

import javafx.fxml.FXMLLoader;
import controllers.Controller;
import sample.MainSystem;

/**
 * The type Main page loader.
 */
public class MainPageLoader extends Loader {
    /**
     * Instantiates a new Main page loader.
     *
     * @param system the system
     */
    public MainPageLoader(MainSystem system) {
        super(system);
    }

    @Override
    void setTitle() {
        title = "Lab 2";
    }

    @Override
    void setPagePath() {
        pagePath = "main.fxml";
    }

    @Override
    void setControllerParameters(FXMLLoader loader) {
        ((Controller)loader.getController()).init();
    }
}
