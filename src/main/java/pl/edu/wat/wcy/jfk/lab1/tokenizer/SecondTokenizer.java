package pl.edu.wat.wcy.jfk.lab1.tokenizer;

import pl.edu.wat.wcy.jfk.lab1.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SecondTokenizer implements ITokenizer {
    private static final String DELIM = " ,+-*()";
    private String text;


    private List<Token> tokenList;
    private int listPosition;

    public SecondTokenizer(String text) {
        this.text = text;

        this.tokenList = new ArrayList<>();
        this.listPosition = -1;

        splitTextToTokens();
    }

    private void splitTextToTokens() {
        StringTokenizer tokenizer = new StringTokenizer(text, DELIM, true);
        while (tokenizer.hasMoreTokens()) {
            String tokenValue = tokenizer.nextToken();
            if (" ".equals(tokenValue))
                continue;
            tokenList.add(new Token(tokenValue, 0, 0, getTokenType(tokenValue)));
        }
    }

    private Token.Type getTokenType(String tokenValue) {
        if (tokenValue.equals("mov")) return Token.Type.MOV;
        else if (tokenValue.equals("push")) return Token.Type.PUSH;
        else if (tokenValue.equals("int")) return Token.Type.INT;
        else if (tokenValue.equals("0x80")) return Token.Type.INT_NUMBER;
        else if (tokenValue.equals("xor")) return Token.Type.XOR;
        else if (tokenValue.matches("[0-9]+")) return Token.Type.NUM;
        else if (tokenValue.matches("%e[a-d]x")) return Token.Type.REG;
        else if (tokenValue.equals("+")) return Token.Type.PLUS;
        else if (tokenValue.equals("-")) return Token.Type.MINUS;
        else if (tokenValue.equals("*")) return Token.Type.MULT;
        else if (tokenValue.equals("(")) return Token.Type.LPAR;
        else if (tokenValue.equals(")")) return Token.Type.RPAR;
        else if (tokenValue.equals(",")) return Token.Type.COMMA;
        throw new RuntimeException("No token type for this value " + tokenValue);
    }

    @Override
    public Token getCurrentToken() {
        if (listPosition == -1 || listPosition >= tokenList.size())
            return new Token("Null", 0, 0, null);
        return tokenList.get(listPosition);
    }

    @Override
    public Token getNextToken() {
        listPosition++;
        if (listPosition >= tokenList.size())
            return new Token("Null", 0, 0, null);
        return tokenList.get(listPosition);
    }

    @Override
    public boolean hasNextToken() {
        return (listPosition + 1) < tokenList.size();
    }
}
