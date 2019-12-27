package brainfuck.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class Controller {
    @FXML
    MenuBar menuBar;
    @FXML
    MenuItem saveMenu;

    public void initialize() {
        saveMenu.setOnAction(e -> {
            System.out.println("menu clicked");
        });

    }

}
