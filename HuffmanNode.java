public class HuffmanNode {
    int data;
    HuffmanNode left, right;
    char character;

    public HuffmanNode(int data, char character) {
        this.data = data;
        this.character = character;
        this.left = null;
        this.right = null;
    }
}
