package pl.edu.wat.wcy.jfk.lab1.interpreter;

import pl.edu.wat.wcy.jfk.lab1.Node;
import pl.edu.wat.wcy.jfk.lab1.Token;
import pl.edu.wat.wcy.jfk.lab1.parser.Parser;
import pl.edu.wat.wcy.jfk.lab1.tokenizer.Tokenizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interpreter {

    public static final String UNKNOWN_VALUE = "???";
    public static final String PRINT_VALUE_FROM_STACK_CODE = "0x80";


    private Map<String, Integer> valueMap;
    private List<Integer> stack;

    public Interpreter() {
        this.valueMap = new HashMap<>();
        this.stack = new ArrayList<>();
    }

    public void interpret(String text) {
        Parser parser = new Parser(new Tokenizer(text));

        Node rootNode = parser.parse();
        switch (rootNode.getToken().getType()) {
            case MOV:
                mov(rootNode);
                break;
            case PUSH:
                push(rootNode);
                break;
            case INT:
                interrupt(rootNode);
                break;
            case XOR:
                xor(rootNode);
                break;
        }

    }

    private void mov(Node rootNode) {
        String registerName = rootNode.getRight().getToken().getValue();
        Integer value = visit(rootNode.getLeft());
        valueMap.put(registerName, value);
    }

    private void push(Node rootNode) {
        Integer valueToAdd = visit(rootNode.getLeft());
        stack.add(0, valueToAdd);
    }

    private void interrupt(Node rootNode) {
        String interruptCode = rootNode.getLeft().getToken().getValue();
        if (PRINT_VALUE_FROM_STACK_CODE.equals(interruptCode))
            printValueFromStack();

    }

    private void printValueFromStack() {
        if (stack.size() == 0)
            return;
        Integer value = stack.remove(0);
        if (value != null)
            System.out.println(value);
        else
            System.out.println(UNKNOWN_VALUE);
    }

    private void xor(Node rootNode) {
        if (rootNode.getLeft().getToken().getValue().equals(rootNode.getRight().getToken().getValue())) {
            valueMap.put(rootNode.getRight().getToken().getValue(), 0);
            return;
        }
        Integer leftValue = visit(rootNode.getLeft());
        Integer registerValue = visit(rootNode.getRight());

        if(leftValue == null || registerValue == null)
            valueMap.put(rootNode.getRight().getToken().getValue(), null);
        else
            valueMap.put(rootNode.getRight().getToken().getValue(), leftValue^registerValue);
    }

    private Integer visit(Node node) {

        Integer leftValue;
        Integer rightValue;

        switch (node.getToken().getType()) {
            case NUM:
                return Integer.parseInt(node.getToken().getValue());
            case REG:
                String registerName = node.getToken().getValue();
                return valueMap.getOrDefault(registerName, null);
            case PLUS:
                leftValue = visit(node.getLeft());
                rightValue = visit(node.getRight());
                if (leftValue == null || rightValue == null)
                    return null;
                return leftValue + rightValue;
            case MINUS:
                leftValue = visit(node.getLeft());
                rightValue = visit(node.getRight());
                if (leftValue == null || rightValue == null)
                    return null;
                return leftValue - rightValue;
            case MULT:
                leftValue = visit(node.getLeft());
                rightValue = visit(node.getRight());
                if (leftValue == null || rightValue == null)
                    return null;
                return leftValue * rightValue;
        }
        throw new RuntimeException("visit()");
    }
}
