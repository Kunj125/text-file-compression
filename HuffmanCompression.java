import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCompression {
    private static Map<Character, String> huffmanCodes = new HashMap<>();
    private static HuffmanNode root;
    static Map<Character, Integer> frequencyMap = new HashMap<>();

    public static void huffmanTree(String text) {
        // gets the frequency of the characters
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

    public static String compress(String text) {
        StringBuilder compressedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            compressedText.append(huffmanCodes.get(c));
        }
        return compressedText.toString();
    }

    public static void main(String[] args) {
        String text = "Hello World";
        huffmanTree(text);
        System.out.println(huffmanCodes);

        String compressedText = compress(text);
        System.out.println("Original text: " + text);
        System.out.println("Compressed text: " + compressedText);
        double originalSize = (text.length() * 8);
        double compressedSize = compressedText.length();
        double compressionRatio = originalSize / compressedSize;
        System.out.println("Size of the original string: " + originalSize);
        System.out.println("Size of the compressed string: " + compressedSize);
        System.out.println("Compression ratio: " + compressionRatio);
        // compression ratio
        // int totalFrequency = 0;
        // int freqCode = 0;
        // for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
        // Character key = entry.getKey();
        // Integer value = entry.getValue();
        // totalFrequency += value;
        // if (huffmanCodes.containsKey(key)) {
        // freqCode += value * huffmanCodes.get(key).length();
        // }
        // }
        // System.out.println(huffmanCodes);
        // double compressedSize = freqCode + totalFrequency + (frequencyMap.size() *
        // 8);
        // System.out.println("Size of the compressed string: " + (compressedSize));
        // System.out.println("Compression Ratio: " + originalSize/compressedSize);
    }
}