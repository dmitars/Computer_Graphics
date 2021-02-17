package sample;

import factory.LoaderFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import loaders.Loader;
import controllers.PageType;

/**
 * The type Main system.
 */
public class MainSystem extends Application {
    private Stage stage;

    /**
     * Gets stage.
     *
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        startDialog(PageType.MAIN);
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start dialog.
     *
     * @param pageType the page type
     */
    public void startDialog(PageType pageType){
        LoaderFactory factory = new LoaderFactory(this);
        Loader loader = factory.getLoader(pageType);
        loader.load();
    }

}
