package pl.edu.wat.wcy.jfk.lab1;

import pl.edu.wat.wcy.jfk.lab1.interpreter.Interpreter;

import java.util.Scanner;

public class Main {

    public static final String EXIT = "exit";
    public static final String PROMPT = ">";
    public static final String ERROR = "Error";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Interpreter interpreter = new Interpreter();
        String text = null;

        while (!EXIT.equals(text)) {
            System.out.print(PROMPT);
            text = input.nextLine();

            try {
                interpreter.interpret(text);
            } catch (RuntimeException e) {
                System.out.println(ERROR);
            }
        }
    }

}
