package brainfuck.interpreter;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Main
 */
public class Main extends Application {
    private Stage stage;

    public static void main(String[] args) {
        String excl = ">,[[----------[                    >>>[>>>>]+[[-]+<[->>>>++>>>>+[>>>>]++[->+<<<<<]]<<<]                    ++++++[>------<-]>--[>>[->>>>]+>+[<<<<]>-],<                ]>            ]>>>++>+>>[                <<[>>>>[-]+++++++++<[>-<-]+++++++++>[-[<->-]+[<<<<]]<[>+<-]>]                >[>[>>>>]+[[-]<[+[->>>>]>+<]>[<+>[<<<<]]+<<<<]>>>[->>>>]+>+[<<<<]]                >[[>+>>[<<<<+>>>>-]>]<<<<[-]>[-<<<<]]>>>>>>>            ]>>+[[-]++++++>>>>]<<<<[[<++++++++>-]<.[-]<[-]<[-]<]<,        ]";
        launch(Main.class);

    }
    //TODO change alert style
 
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent pane = FXMLLoader.load(getClass().getResource("/fxml/layout.fxml"));
        Scene scene = new Scene(pane);
        scene.getStylesheets().add("/css/menuBar.css");
        scene.getStylesheets().add("/css/stylesheet.css");
        stage.setTitle("Brainfuck Interpreter");
        stage.setScene(scene);
        stage.show();

    }

    public Stage getStage() {
        return this.stage;
    }
}