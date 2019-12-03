package brainfuck.interpreter;

import java.io.UnsupportedEncodingException;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        String excl = "++++++++++[>+>+++>+++++++>++++++++++<<<<-]>>>>-----------.++++++++++++..+++++++++++++++.<<++.>+++++++.>-------------------.+++++++++++++.---.------.+++++++++++++.<<+.";

        try {
            BfInterpreter interpreter = new BfInterpreter(excl);
            interpreter.interpret();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}