import java.util.*;
public class FindDuplicateStrings {
    public static void main(String[] args) {
        //String name = "Hello World Hello";
        // char[] chars = name.toCharArray();
        // for (char c : chars) {
        //     int count = 0;
        //     for (char ch : chars) {
        //         if (c == ch) {
        //             count++;
        //         }
        //     }
        //     if (count > 1) {
        //         System.out.println(c + " is a duplicate character");
        //     }
        // }
        String str = "programming";

        Map<Character, Integer> map = new HashMap<>();

        for(char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        map.entrySet()
        .stream()
        .filter(e -> e.getValue() > 1)
        .forEach(System.out::println);
        }
}
