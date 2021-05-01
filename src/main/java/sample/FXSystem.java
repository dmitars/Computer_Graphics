package sample;

import controllers.PageType;
import javafx.stage.Stage;

import java.net.URL;

public interface FXSystem {
    void startDialog(PageType pageType);
    Stage getStage();
    URL getPagePath(String name);
}
