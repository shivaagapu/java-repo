public class CheckEqualAndHashCode {
    public static void main(String[] args) {
        String name1 = new String("Alice");
        String name2 = name1; // name2 references the same object as name1
         System.out.println("Using == operator: " + (name1 == name2)); // true
        name2 = new String("Alice"); // name2 now references a different object with the same content
        System.out.println("Using equals(): " + name1.equals(name2)); // true
        System.out.println("Using == operator: " + (name1 == name2)); // false
    }
}
