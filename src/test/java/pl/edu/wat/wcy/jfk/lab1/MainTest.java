package pl.edu.wat.wcy.jfk.lab1;

import org.junit.Test;
import pl.edu.wat.wcy.jfk.lab1.interpreter.Interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainTest {

    @Test
    public void test1() {
        List<String> testList = new ArrayList<>(Arrays.asList(
                "mov (4+6)*2, %edx",
                "push %edx*2",
                "int 0x80",
                "push %edx",
                "push %ecx",
                "int 0x80",
                "int 0x80",
                "mov 4 - 1, %ecx ",
                "mov 4 - , %ecx",
                "push %ecx",
                "int 0x80",
                "mov 24, %ecx",
                "xor %ebx, %ebx",
                "mov %ebx + 3 + %edx*2 + 5 - %ecx, %ecx",
                "push %edx + %ecx + %ebx",
                "int 0x80",
                "push 215",
                "int 0x80",
                "mov 215, addddd  ",
                "mov %ecx, 215  "
                ));

        Interpreter interpreter = new Interpreter();

        testList.forEach(s -> {
            try {
                interpreter.interpret(s);
            } catch (RuntimeException e) {
                System.out.println("Error");
            }
        });
    }
}