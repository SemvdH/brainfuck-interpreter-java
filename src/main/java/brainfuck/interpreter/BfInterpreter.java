package brainfuck.interpreter;

import java.io.FileInputStream;
import java.io.IOException;
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

        FileInputStream fis = new FileInputStream("src/main/java/resources/" + fileName);
        code = IOUtils.toString(fis, "UTF-8");
        // System.out.println("code is: " + code);

    }

    public void interpret() {
        int loop = 0;
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

    }

    public void interpretAgain(String code) {
        final int LENGTH = 65535;
        byte[] mem = new byte[LENGTH];
        int dataPointer = 0;
        int l = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '>') {
                dataPointer = (dataPointer == LENGTH - 1) ? 0 : dataPointer + 1;
            } else if (code.charAt(i) == '<') {
                dataPointer = (dataPointer == 0) ? LENGTH - 1 : dataPointer - 1;
            } else if (code.charAt(i) == '+') {
                mem[dataPointer]++;
            } else if (code.charAt(i) == '-') {
                mem[dataPointer]--;
            } else if (code.charAt(i) == '.') {
                System.out.print((char) mem[dataPointer]);
            } else if (code.charAt(i) == ',') {
                mem[dataPointer] = (byte) sc.next().charAt(0);
            } else if (code.charAt(i) == '[') {
                if (mem[dataPointer] == 0) {
                    i++;
                    while (l > 0 || code.charAt(i) != ']') {
                        if (code.charAt(i) == '[')
                            l++;
                        if (code.charAt(i) == ']')
                            l--;
                        i++;
                    }
                }
            } else if (code.charAt(i) == ']') {
                if (mem[dataPointer] != 0) {
                    i--;
                    while (l > 0 || code.charAt(i) != '[') {
                        if (code.charAt(i) == ']')
                            l++;
                        if (code.charAt(i) == '[')
                            l--;
                        i--;
                    }
                    i--;
                }
            }
        }
    }

    @Deprecated
    public void interpretSwitch() {
        int pointer = 0;
        int c = 0;
        for (int i = 0; i < codeArray.length; i++) {

            switch (this.codeArray[i]) {
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
            case '[':

                if (memory[pointer] == 0) {
                    i++;
                    while (c > 0 || this.codeArray[i] != ']') {
                        if (this.codeArray[i] == '[') {
                            c++;
                        } else if (this.codeArray[i] == ']') {
                            c--;
                        }
                        i++;
                    }
                }
                // int endLoopPos = i;
                // // search for next ']'
                // while (code[endLoopPos] != ']') {
                // // keep incrementing until end of loop is found
                // endLoopPos++;
                // }
                // if (memory[pointer] == 0) {
                // i = endLoopPos + 1;
                // }
                break;
            case ']':

                if (memory[pointer] != 0) {
                    i--;
                    while (c > 0 || this.codeArray[i] == '[') {
                        if (this.codeArray[i] == ']') {
                            c++;
                        } else if (this.codeArray[i] == '[') {
                            c--;
                        }
                        i--;
                    }
                    i--;
                }
                // int beginLoopPos = i;
                // // search for previous '['
                // while (code[beginLoopPos] != '[') {
                // // keep decrementing until begin of loop is found
                // beginLoopPos--;
                // }

                // if (memory[pointer] != 0) {
                // i = beginLoopPos;
                // }
                break;

            default:
                break;
            }
        }
    }

}