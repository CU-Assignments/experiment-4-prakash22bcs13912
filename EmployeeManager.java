import java.util.*;

class Employee {
    int id;
    String name;
    double salary;

    // Constructor to initialize the employee details
    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Method to display employee details
    void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);
    }
}

public class EmployeeManager {
    // ArrayList to store Employee objects
    static ArrayList<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Loop to show the menu
        do {
            System.out.println("\n1. Add Employee\n2. Update Employee\n3. Remove Employee\n4. Search Employee\n5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Add Employee
                    System.out.print("Enter ID, Name, and Salary: ");
                    int id = scanner.nextInt();
                    String name = scanner.next();
                    double salary = scanner.nextDouble();
                    employees.add(new Employee(id, name, salary));
                    System.out.println("Employee added successfully.");
                    break;

                case 2:
                    // Update Employee
                    System.out.print("Enter ID to update: ");
                    id = scanner.nextInt();
                    boolean updated = false;
                    for (Employee emp : employees) {
                        if (emp.id == id) {
                            System.out.print("Enter new Name and Salary: ");
                            name = scanner.next();
                            salary = scanner.nextDouble();
                            emp.name = name;
                            emp.salary = salary;
                            updated = true;
                            System.out.println("Employee updated successfully.");
                            break;
                        }
                    }
                    if (!updated) {
                        System.out.println("Employee with ID " + id + " not found.");
                    }
                    break;

                case 3:
                    // Remove Employee
                    System.out.print("Enter ID to remove: ");
                    id = scanner.nextInt();
                    Iterator<Employee> iterator = employees.iterator();
                    boolean removed = false;
                    while (iterator.hasNext()) {
                        Employee emp = iterator.next();
                        if (emp.id == id) {
                            iterator.remove();  // Safe way to remove elements while iterating
                            removed = true;
                            System.out.println("Employee removed successfully.");
                            break;
                        }
                    }
                    if (!removed) {
                        System.out.println("Employee with ID " + id + " not found.");
                    }
                    break;

                case 4:
                    // Search Employee
                    System.out.print("Enter ID to search: ");
                    id = scanner.nextInt();
                    boolean found = false;
                    for (Employee emp : employees) {
                        if (emp.id == id) {
                            emp.display();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Employee with ID " + id + " not found.");
                    }
                    break;

                case 5:
                    // Exit the program
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}  // Make sure this closing brace is here to close the EmployeeManager class
