package pl.edu.wat.wcy.jfk.lab1.tokenizer;

import pl.edu.wat.wcy.jfk.lab1.Token;

public interface ITokenizer {

    Token getCurrentToken();

    Token getNextToken();

    boolean hasNextToken();
}
