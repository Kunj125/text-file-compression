import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCompression {
    private static Map<Character, String> huffmanCodes = new HashMap<>();
    private static HuffmanNode root;

    public static void huffmanTree(String text) {
        // gets the frequency of the characters
        Map<Character, Integer> frequencyMap = new HashMap<>();
        
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        // creates a leaf node for each character
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.offer(new HuffmanNode(entry.getValue(), entry.getKey()));
        }
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode combined = new HuffmanNode(left.data + right.data, '-');
            combined.left = left;
            combined.right = right;
            priorityQueue.offer(combined);
        }
        root = priorityQueue.peek();
        generateHuffmanCodes(root, new StringBuilder());
    }

    public static void generateHuffmanCodes(HuffmanNode node, StringBuilder code) {
        if (node == null) {
            return;
        }

        if (node.character != '-') {
            huffmanCodes.put(node.character, code.toString());
        }

        code.append("0");
        generateHuffmanCodes(node.left, code);
        code.deleteCharAt(code.length() - 1);

        code.append("1");
        generateHuffmanCodes(node.right, code);
        code.deleteCharAt(code.length() - 1);
    }

    public static void main(String[] args) {
        String text = "Hello world";
        huffmanTree(text);

    }
}