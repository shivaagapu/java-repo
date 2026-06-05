final class EmpObj {

    private final String name;
    private final int id;

    public EmpObj(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    
}

public class StringFinalEmpObj {
    public static void main(String[] args) {
        EmpObj emp1 = new EmpObj("Alice", 1);
        System.out.println("Employee Name: " + emp1.getName());
        System.out.println("Employee ID: " + emp1.getId());

        // The following lines would cause compilation errors because the fields are final
        // emp1.name = "Bob"; // Error: cannot assign a value to final variable name
        // emp1.id = 2; // Error: cannot assign a value to final variable id
    }
}       
