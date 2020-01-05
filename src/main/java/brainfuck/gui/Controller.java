package brainfuck.gui;

import brainfuck.interpreter.BfInterpreter;
import brainfuck.interpreter.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

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

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label statusText;

    public void initialize() {
        changeStatus("Idle", "#9a9c9a");
        saveMenu.setOnAction(e -> {
            System.out.println("menu clicked");
        });

        interpretButton.setOnAction(e -> {
            changeStatus("Interpreting...", "#28fff5");
            try {
                interpretFromText();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            changeStatus("Done!", "#00ff12");
        });

        loadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Brainfuck file", ".bf", ".b"));
            fileChooser.setTitle("Choose file to import");

            changeStatus("Interpreting...", "#28fff5");
            try {
                File selected = fileChooser.showOpenDialog(rootPane.getScene().getWindow());

                if (selected == null) {
                    changeStatus("Idle", "#9a9c9a");
                    return;
                }

                FileInputStream fileInputStream = new FileInputStream(selected);
                String input = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);

                textinput.setText(input);
                BfInterpreter interpreter = new BfInterpreter("");
                interpreter.setCodeFromFile(selected);
                String interpreted = interpreter.interpret();
                textoutput.setText(interpreted);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            changeStatus("Done!", "#00ff12");
        });

        // interpretButton.setSkin(new ScaleSkin(interpretButton));
    }

    private void changeStatus(String text, String color) {
        statusText.setStyle("-fx-text-fill: " + color);
        statusText.setText(text);
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
