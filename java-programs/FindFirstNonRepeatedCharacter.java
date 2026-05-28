import java.util.HashMap;
import java.util.Map;

public class FindFirstNonRepeatedCharacter {
    public static void main(String[] args) {
        String str = "programming";
        Map<Character, Integer> map = new HashMap<>();

        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : str.toCharArray()) {
            if (map.get(c) == 1) {
                System.out.println("First non-repeated character: " + c);
                break;
            }
        }
    }
}
