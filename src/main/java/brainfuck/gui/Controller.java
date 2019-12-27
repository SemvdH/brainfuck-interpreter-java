package brainfuck.gui;

import brainfuck.interpreter.BfInterpreter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class Controller {
    @FXML
    private MenuBar menuBar;

    @FXML
    private TextArea textoutput;

    @FXML
    private Button interpretButton;

    @FXML
    private MenuItem saveMenu;

    @FXML
    private TextArea textinput;

    @FXML
    private Button loadButton;

    public void initialize() {
        saveMenu.setOnAction(e -> {
            System.out.println("menu clicked");
        });

        interpretButton.setOnAction(e -> {
            try {
                interpretFromText();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // interpretButton.setSkin(new ScaleSkin(interpretButton));
    }

    private void interpretFromText() throws Exception {
        String inputCode = textinput.getText();
        if (inputCode == null || inputCode.isEmpty()) {
            CustomAlert alert = new CustomAlert(AlertType.ERROR, "Nothing entered!", "Please enter some brainfuck code!", "No code entered");
            alert.showAndWait();
        } else {
            BfInterpreter interpreter = new BfInterpreter(inputCode);
            String interpreted = interpreter.interpret();
            textoutput.setText(interpreted);
        }

        
    }

}
