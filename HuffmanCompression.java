import java.util.HashMap;
import java.util.Map;

public class HuffmanCompression {
    public static Map<Character, Integer> huffmanTree(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        return frequencyMap;

    }

    public static void main(String[] args) {
        String text = "Hello world";
        System.out.println(huffmanTree(text));
    }
}