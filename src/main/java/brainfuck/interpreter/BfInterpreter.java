package brainfuck.interpreter;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Brainfuck interpreter.
 * 
 * @author Sem van der Hoeven
 */
public class BfInterpreter {

    private char[] code;
    private byte[] memory;
    private Scanner sc;

    public final int MAX_SIZE = 65535; // predefined max size for the array

    public BfInterpreter(String code) throws Exception {
        if (code == null) {
            throw new Exception("The code cannot be null!");
        }

        this.sc = new Scanner(System.in);
        this.code = code.toCharArray();
        this.memory = new byte[MAX_SIZE];
    }

    public void interpret() {
        //  ++++++++++[>+>+++>+++++++>++++++++++<<<<-]>>++++++++++++++++++...
        int pointer = 0;
        for (int i = 0; i < this.code.length; i++) {
            // System.out.println("char is now " + this.code[i]);
            switch (this.code[i]) {
            case '+':
                memory[pointer]++;
                break;
            case '-':
                memory[pointer]--;
                break;
            case '>':
                pointer++;
                break;
            case '<':
                pointer--;
                break;
            case '.':
                System.out.print(String.valueOf((char) memory[pointer]));
                break;
            case ',':
                memory[pointer] = sc.next().trim().getBytes()[0];
                break;
            case '[': // als de memory[pointer] 0 is, spring naar de plek na de volgende ] ipv een
                      // vooruit
                // System.out.println("begin loop cell is " + memory[i]);
                int endLoopPos = i;
                // search for next ']'
                while (code[endLoopPos] != ']') {
                    // keep incrementing until end of loop is found
                    endLoopPos++;
                }
                // System.out.println("end loop pos: " + endLoopPos);
                if (memory[pointer] == 0) {
                    i = endLoopPos + 1;
                } 

                break;
            case ']': // als de memory[pointer] niet 0 is, spring terug naar de plek voor de vorige [
                      // ipv een vooruit
                int beginLoopPos = i;
                // System.out.println("eind loop");
                // search for previous '['
                while (code[beginLoopPos] != '[') {
                    // keep decrementing until begin of loop is found
                    beginLoopPos--;
                }
                // System.out.println("begin loop pos: " + beginLoopPos);

                if (memory[pointer] != 0) {
                    i = beginLoopPos;
                } 
                break;

            default:
                break;
            }
        }

    }

}