class Palindrome {
public static void main (String args[]) {
String name = "madam";
String reverse = "";
for (int i = name.length()-1; i >= 0; i--) {
reverse += name.charAt(i);
}
if(name.equals(reverse)){
System.out.println(name + " is a palindrome");
}
else{
System.out.println(name + " is not a palindrome");
}
}
}
