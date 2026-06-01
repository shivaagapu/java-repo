import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CompareToCompare {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 1, 50000),
                new Employee("Bob", 2, 60000),
                new Employee("Charlie", 3, 50000)
        );
        Collections.sort(employees);
        System.out.println("Employees sorted by salary:");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
        // Using lambda expression to sort by name
        employees.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
        System.out.println("Employees sorted by name:");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
        // Using method reference to sort by id
        employees.sort((e1, e2) -> Integer.compare(e1.getId(), e2.getId()));
        System.out.println("Employees sorted by id:");  
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
}

class Employee implements Comparable<Employee> {
    private String name;
    private int id;
    private double salary;

    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public int compareTo(Employee other) {
        return Double.compare(this.salary, other.salary);
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', id=" + id + ", salary=" + salary + "}";
    }
}


