package brainfuck.interpreter;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main
 */
public class Main extends Application {

    public static void main(String[] args) {
        String excl = ">,[[----------[                    >>>[>>>>]+[[-]+<[->>>>++>>>>+[>>>>]++[->+<<<<<]]<<<]                    ++++++[>------<-]>--[>>[->>>>]+>+[<<<<]>-],<                ]>            ]>>>++>+>>[                <<[>>>>[-]+++++++++<[>-<-]+++++++++>[-[<->-]+[<<<<]]<[>+<-]>]                >[>[>>>>]+[[-]<[+[->>>>]>+<]>[<+>[<<<<]]+<<<<]>>>[->>>>]+>+[<<<<]]                >[[>+>>[<<<<+>>>>-]>]<<<<[-]>[-<<<<]]>>>>>>>            ]>>+[[-]++++++>>>>]<<<<[[<++++++++>-]<.[-]<[-]<[-]<]<,        ]";

        try {
            BfInterpreter interpreter = new BfInterpreter(",.");
            interpreter.setCodeFromFile("mandelbrot-tiny.bf");
            interpreter.interpret();
            // interpreter.interpretAgain(excl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        

    }
}