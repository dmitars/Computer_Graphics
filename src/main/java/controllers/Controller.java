package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.MainSystem;

/**
 * The type Controller.
 */
public abstract class Controller implements ErrorDisplay {
    /**
     * The System.
     */
    MainSystem system;

    /**
     * The Error label.
     */
    @FXML
    Label errorLabel;

    /**
     * Sets control system.
     *
     * @param mainSystem the main system
     */
    public void setControlSystem(MainSystem mainSystem) {
        this.system = mainSystem;
    }

    /**
     * Init.
     */
    public abstract void init();

    public void showErrorMessage(String message) {
        errorLabel.setText(message);
    }

    /**
     * Refresh.
     */
    public abstract void refresh();

    /**
     * Close.
     */
    public abstract void close();
}
