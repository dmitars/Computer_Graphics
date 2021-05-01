package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.FXSystem;

/**
 * The type Controller.
 */
public abstract class Controller implements ErrorDisplay {
    /**
     * The System.
     */
    FXSystem system;

    /**
     * The Error label.
     */
    @FXML
    Label errorLabel;

   /* *//**
     * Sets control system.
     *
     * @param system the main system
     */
    public void setControlSystem(FXSystem system) {
        this.system = system;
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
