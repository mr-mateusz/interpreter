package pl.edu.wat.wcy.jfk.lab1.tokenizer;

import pl.edu.wat.wcy.jfk.lab1.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer implements ITokenizer {
    private String text;

    private List<Token> tokenList;
    private int position;

    public Tokenizer(String text) {
        this.text = text;
        this.position = -1;

        this.tokenList = new ArrayList<>();

        String stringRegex = "[a-z]{3,4}|0x80|\\d+|%e[a-d]x|\\+|-|\\*|,|\\(|\\)";

        Pattern pattern = Pattern.compile(stringRegex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String tokenValue = matcher.group();
            int startPos = matcher.start();
            int endPos = matcher.end();
            Token.Type type = getTokenType(tokenValue);
            tokenList.add(new Token(tokenValue, startPos, endPos, type));
        }
    }

    @Override
    public Token getCurrentToken() {
        if (position == -1 || position >= tokenList.size())
            return new Token("Null", 0, 0, null);
        return tokenList.get(position);
    }

    @Override
    public Token getNextToken() {
        position++;
        if (position >= tokenList.size())
            return new Token("Null", 0, 0, null);
        return tokenList.get(position);
    }

    @Override
    public boolean hasNextToken() {
        return position + 1 < tokenList.size();
    }

    private Token.Type getTokenType(String tokenValue) {
        if (tokenValue.equals("mov")) {
            return Token.Type.MOV;
        } else if (tokenValue.equals("push")) {
            return Token.Type.PUSH;
        } else if (tokenValue.equals("int")) {
            return Token.Type.INT;
        } else if (tokenValue.equals("0x80")) {
            return Token.Type.INT_NUMBER;
        } else if (tokenValue.equals("xor")) {
            return Token.Type.XOR;
        } else if (tokenValue.matches("[0-9]+")) {
            return Token.Type.NUM;
        } else if (tokenValue.matches("%e[a-d]x")) {
            return Token.Type.REG;
        } else if (tokenValue.equals("+")) {
            return Token.Type.PLUS;
        } else if (tokenValue.equals("-")) {
            return Token.Type.MINUS;
        } else if (tokenValue.equals("*")) {
            return Token.Type.MULT;
        } else if (tokenValue.equals("(")) {
            return Token.Type.LPAR;
        } else if (tokenValue.equals(")")) {
            return Token.Type.RPAR;
        } else if (tokenValue.equals(",")) {
            return Token.Type.COMMA;
        }
        throw new RuntimeException("No token type for this value " + tokenValue);
    }
}
