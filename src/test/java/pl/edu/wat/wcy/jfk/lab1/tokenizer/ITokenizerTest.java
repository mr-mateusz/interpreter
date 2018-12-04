package pl.edu.wat.wcy.jfk.lab1.tokenizer;

import org.junit.Assert;
import org.junit.Test;

public class ITokenizerTest {

    @Test
    public void compareTokenizers() {
        String text = "mov %ebx + 3 + %edx*2 + 5 - %ecx, %ecx";

        ITokenizer firstTokenizer = new Tokenizer(text);
        ITokenizer secondTokenizer = new SecondTokenizer(text);

        while (firstTokenizer.hasNextToken()) {
            Assert.assertEquals(firstTokenizer.getNextToken().getValue(), secondTokenizer.getNextToken().getValue());
            System.out.println(firstTokenizer.getCurrentToken().getValue());
        }
    }
}