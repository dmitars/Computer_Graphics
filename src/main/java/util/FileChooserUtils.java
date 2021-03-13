package util;

import javafx.stage.FileChooser;

/**
 * The type File chooser utils.
 */
public class FileChooserUtils {
    /**
     * Get image file chooser file chooser.
     *
     * @return the file chooser
     */
    public static FileChooser getImageFileChooser(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG);
        fileChooser.setTitle("Select image file");
        return fileChooser;
    }
}
