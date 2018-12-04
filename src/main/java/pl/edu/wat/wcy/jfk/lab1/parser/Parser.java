package pl.edu.wat.wcy.jfk.lab1.parser;

import pl.edu.wat.wcy.jfk.lab1.Node;
import pl.edu.wat.wcy.jfk.lab1.Token;
import pl.edu.wat.wcy.jfk.lab1.tokenizer.ITokenizer;

public class Parser {
    private ITokenizer tokenizer;

    public Parser(ITokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public Node parse() {
        Token token = tokenizer.getNextToken();

        switch (token.getType()) {
            case MOV:
                return new Node(token, expression(), register());
            case INT:
                return new Node(token, interruptNumber(), null);
            case PUSH:
                return new Node(token, expression(), null);
            case XOR:
                return new Node(token, expression(), register());
        }
        throw new RuntimeException("parse()");
    }

    private Node interruptNumber() {
        Token token = tokenizer.getNextToken();
        if (token.getValue().matches("0x[0-9]{2}"))
            return new Node(token, null, null);
        throw new RuntimeException("interruptNumber()");
    }

    private Node register() {
        Token token = tokenizer.getNextToken();
        if (token.getType() == Token.Type.REG)
            return new Node(token, null, null);
        throw new RuntimeException("register()");
    }

    private Node expression() {
        Node node = expression2();
        while (tokenizer.getCurrentToken().getType() == Token.Type.PLUS || tokenizer.getCurrentToken().getType() == Token.Type.MINUS)
            node = new Node(tokenizer.getCurrentToken(), node, expression2());
        return node;
    }

    private Node expression2() {
        Node node = expression3();
        while (tokenizer.getCurrentToken().getType() == Token.Type.MULT)
            node = new Node(tokenizer.getCurrentToken(), node, expression3());
        return node;
    }

    private Node expression3() {
        Token token = tokenizer.getNextToken();

        switch (token.getType()) {
            case REG:
                tokenizer.getNextToken();
                return new Node(token, null, null);
            case NUM:
                tokenizer.getNextToken();
                return new Node(token, null, null);
            case LPAR:
                Node node = expression();
                if (tokenizer.getCurrentToken().getType() != Token.Type.RPAR)
                    throw new RuntimeException("expression3()");
                tokenizer.getNextToken();
                return node;
        }
        throw new RuntimeException("expression3()");
    }
}
