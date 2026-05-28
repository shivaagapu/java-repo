public class FindEmpBySalary {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Alice", 1, 50000);
        Employee emp2 = new Employee("Bob", 2, 60000);
        Employee emp3 = new Employee("Charlie", 3, 50000);

        Employee[] employees = {emp1, emp2, emp3};

        int targetSalary = 50000;
        System.out.println("Employees with salary " + targetSalary + ":");
        for (Employee emp : employees) {
            if (emp.getSalary() == targetSalary) {
                System.out.println(emp);
            }
        }

        //Second way using streams
        System.out.println("Using Streams:");
        java.util.Arrays.stream(employees)
                .filter(emp -> emp.getSalary() == targetSalary)
                .forEach(System.out::println);  

        // Second highest salary
        System.out.println("Second highest salary:");
        java.util.Arrays.stream(employees)
                .map(Employee::getSalary)
                .distinct()
                .sorted(java.util.Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .ifPresent(System.out::println);        
    }
}
