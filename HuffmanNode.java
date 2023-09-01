public class HuffmanNode implements Comparable<HuffmanNode> {
    int data;
    HuffmanNode left, right;
    char character;

    public HuffmanNode(int data, char character) {
        this.data = data;
        this.character = character;
        this.left = null;
        this.right = null;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        // TODO Auto-generated method stub
        return this.data - o.data;
    }
}
