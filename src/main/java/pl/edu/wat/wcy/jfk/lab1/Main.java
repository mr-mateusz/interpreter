package pl.edu.wat.wcy.jfk.lab1;

import pl.edu.wat.wcy.jfk.lab1.interpreter.Interpreter;

import java.util.Scanner;

public class Main {

    public static final String EXIT = "exit";
    public static final String PROMPT = "";
    public static final String ERROR = "Error";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Interpreter interpreter = new Interpreter();
        String text = null;


        System.out.print(PROMPT);
        if (input.hasNextLine()) {
            text = input.nextLine();
        } else
            text = EXIT;

        while (!EXIT.equals(text)) {
            try {
                interpreter.interpret(text);
            } catch (RuntimeException e) {
                System.out.println(ERROR);
            }
            System.out.print(PROMPT);
            if (input.hasNextLine())
                text = input.nextLine();
            else
                text = EXIT;
        }
    }

}
