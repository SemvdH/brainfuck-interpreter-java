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

    public final int MAX_SIZE = 65535;

    public BfInterpreter(String code) throws Exception {
        if (code == null) {
            throw new Exception("The code cannot be null!");
        }

        this.sc = new Scanner(System.in);
        this.code = code.toCharArray();
        this.memory = new byte[MAX_SIZE];
    }

    public void interpret() {
        int pointer = 0;
        for (int i = 0; i < this.code.length; i++) {
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
            case '[': // als de memory[pointer] 0 is, spring naar de plek na de volgende ] ipv een vooruit
                    
                break;
            case ']': // als de memory[pointer] niet 0 is, spring terug naar de plek voor de vorige [ ipv een vooruit

                break;

            default:
                break;
            }
        }

    }

}