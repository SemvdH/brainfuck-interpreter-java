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

    public static void main(String[] args) {
        String excl = ">,[[----------[                    >>>[>>>>]+[[-]+<[->>>>++>>>>+[>>>>]++[->+<<<<<]]<<<]                    ++++++[>------<-]>--[>>[->>>>]+>+[<<<<]>-],<                ]>            ]>>>++>+>>[                <<[>>>>[-]+++++++++<[>-<-]+++++++++>[-[<->-]+[<<<<]]<[>+<-]>]                >[>[>>>>]+[[-]<[+[->>>>]>+<]>[<+>[<<<<]]+<<<<]>>>[->>>>]+>+[<<<<]]                >[[>+>>[<<<<+>>>>-]>]<<<<[-]>[-<<<<]]>>>>>>>            ]>>+[[-]++++++>>>>]<<<<[[<++++++++>-]<.[-]<[-]<[-]<]<,        ]";
        launch(Main.class);
        // try {
        //     BfInterpreter interpreter = new BfInterpreter(",.");
        //     interpreter.setCodeFromFile("mandelbrot-tiny.bf");
        //     interpreter.interpret();
        //     // interpreter.interpretAgain(excl);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent pane = FXMLLoader.load(getClass().getResource("/fxml/layout.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }
}