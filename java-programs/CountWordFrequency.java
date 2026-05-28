import java.util.*;
import java.util.stream.Collectors;
public class CountWordFrequency {
    public static void main(String[] args) {
        String str = "hello world hello";
    //     String[] words = str.split(" ");
    //     Map<String, Integer> map = new HashMap<>();
    //     for (String word : words) {
    //         map.put(word, map.getOrDefault(word, 0) + 1);
    //     }
    //     map.entrySet()
    //             .stream()
    //             .filter(e -> e.getValue() > 1)
    //             .forEach(System.out::println);
        Map<String, Long> map = Arrays.stream(str.split(" ")).collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        map.forEach((word, count) -> System.out.println(word + ": " + count));
    }
}
