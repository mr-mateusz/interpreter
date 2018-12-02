package pl.edu.wat.wcy.jfk.lab1;

public class Node {
    private Token token;
    private Node left;
    private Node right;

    public Node(Token token, Node left, Node right) {
        this.token = token;
        this.left = left;
        this.right = right;
    }

    public Token getToken() {
        return token;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}
