import Controller.EmployeeController;
import Entity.Employee;
import Entity.Department;
import Entity.Gender;
import Service.EmployeeService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Employee> employees = new HashSet<>();
        Department hr = new Department(1, "HR");
        Department engineering = new Department(2, "Engineering");
        Department marketing = new Department(3, "Marketing");

        employees.add(new Employee(1, "Alice", hr, LocalDateTime.of(1990, 1, 1, 0, 0), Gender.F, "Hanoi", "Hanoi", "123456789"));
        employees.add(new Employee(2, "Bob", engineering, LocalDateTime.of(1985, 5, 15, 0, 0), Gender.M, "Da Nang", "Da Nang", "987654321"));
        employees.add(new Employee(3, "Charlie", hr, LocalDateTime.of(1992, 3, 30, 0, 0), Gender.M, "Hanoi", "Hanoi", "555555555"));
        employees.add(new Employee(4, "David", marketing, LocalDateTime.of(1988, 8, 22, 0, 0), Gender.M, "Ho Chi Minh", "HCM", "444444444"));

        EmployeeService employeeService = new EmployeeService(employees);

        EmployeeController employeeController = new EmployeeController(employeeService);

        Set<Employee> searchResults = employeeController.searchByName("Alice");
        System.out.println("Kết quả tìm kiếm cho 'Alice': " + searchResults);

        Map<String, Set<Employee>> groupedEmployees = employeeController.groupByDepartment();
        System.out.println("Nhóm nhân viên theo phòng ban:");
        groupedEmployees.forEach((department, empSet) -> {
            System.out.println(department + ": " + empSet);
        });

        Map<String, Integer> employeeCounts = employeeController.countTotal();
        System.out.println("Tổng số nhân viên theo phòng ban:");
        employeeCounts.forEach((department, count) -> {
            System.out.println(department + ": " + count + " nhân viên.");
        });
    }
}
