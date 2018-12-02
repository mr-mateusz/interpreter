package pl.edu.wat.wcy.jfk.lab1;

public class Token {
    private String value;
    private int startPos;
    private int endPos;
    private Type type;

    public Token(String value, int startPos, int endPos, Type type) {
        this.value = value;
        this.startPos = startPos;
        this.endPos = endPos;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        MOV,
        INT,
        PUSH,
        XOR,
        PLUS,
        MINUS,
        MULT,
        LPAR,
        RPAR,
        COMMA,
        REG,
        NUM,
        INT_NUMBER
    }
}
