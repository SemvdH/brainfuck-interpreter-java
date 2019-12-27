package brainfuck.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

/**
 * CustomAlert
 */
public class CustomAlert extends Alert{

    /**
     * makes a new alert
     * @param alert the alertType
     * @param title the title
     * @param message the message
     * @param headerText the header text
     */
    public CustomAlert(AlertType alert, String title, String message, String headerText) {
        super(alert);
        this.setTitle(title);
        this.setContentText(message);
        this.setHeaderText(headerText);

        DialogPane dialogPane = this.getDialogPane();
        dialogPane.getStylesheets().add("/css/stylesheet.css");
    }

    
}