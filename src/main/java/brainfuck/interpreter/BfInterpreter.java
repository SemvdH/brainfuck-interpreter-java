package brainfuck.interpreter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

/**
 * Brainfuck interpreter.
 * 
 * @author Sem van der Hoeven
 */
public class BfInterpreter {

    private char[] codeArray;
    private byte[] memory;
    private Scanner sc;
    private String code;
    private int pointer;

    public final int MAX_SIZE = 65535; // predefined max size for the array

    public BfInterpreter(String code) throws Exception {
        if (code == null) {
            throw new Exception("The code cannot be null!");
        }

        this.sc = new Scanner(System.in);
        this.codeArray = code.toCharArray();
        this.code = code;
        this.memory = new byte[MAX_SIZE];
    }

    public void setCodeFromFile(String fileName) throws IOException, NullPointerException {
        String res = "";

        FileInputStream fis = new FileInputStream(fileName);
        code = IOUtils.toString(fis, StandardCharsets.UTF_8);
        // System.out.println("code is: " + code);

    }

    public void setCodeFromFile(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        code = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
    }

    public String interpret() {
        int loop = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            // System.out.println("char is now " + this.code.charAt(i));

            if (code.charAt(i) == '>') {
                // move the pointer one position to the right
                // first check if the pointer is not at the end of the memory array
                pointer = (pointer == MAX_SIZE - 1) ? 0 : pointer + 1;
            } else if (code.charAt(i) == '<') {
                // move the pointer one position to the left
                // first check if the pointer is not at the beginning of the memory array
                pointer = (pointer == 0) ? MAX_SIZE - 1 : pointer - 1;
            } else if (code.charAt(i) == '+') {
                // increment the value in the array by one
                memory[pointer]++;
            } else if (code.charAt(i) == '-') {
                // decrement the value in the array by one
                memory[pointer]--;
            } else if (code.charAt(i) == '.') {
                // System.out.println("printing");
                // output the value in the array
                System.out.print((char) memory[pointer]);
                result.append((char) memory[pointer]);
            } else if (code.charAt(i) == ',') {
                // the next value in the array is the next input character
                // System.out.println("input");
                byte input = (byte) sc.next().charAt(0);
                // System.out.println(input);
                memory[pointer] = input;
            } else if (code.charAt(i) == '[') {
                // jump to the operation after the corresponding [ if the value in the position
                // of the array is 0
                if (memory[pointer] == 0) {
                    i++;
                    while (loop > 0 || code.charAt(i) != ']') {
                        if (code.charAt(i) == '[')
                            loop++;
                        if (code.charAt(i) == ']')
                            loop--;
                        i++;
                    }
                }
            } else if (code.charAt(i) == ']') {
                // jump to the operation after the corresponding ] if the value in the position
                // of the array is not 0
                if (memory[pointer] != 0) {
                    i--;
                    while (loop > 0 || code.charAt(i) != '[') {
                        if (code.charAt(i) == ']')
                            loop++;
                        if (code.charAt(i) == '[')
                            loop--;
                        i--;
                    }
                    i--;
                }
            }

        }
        sc.close();
        return result.toString();

    }

}