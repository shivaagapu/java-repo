public class HelloWorld {
public static void main(String[] args) {
String name = "Hello World";
String reverse = "";
for ( int i = name.length() -1; i>=0; i--) {
    reverse += name.charAt(i);
}
System.out.println("Hello " + name);
System.out.println("Reversed: " + reverse);
System.out.println("Using StringBuilder: " + new StringBuilder(name).reverse().toString());
}
}
