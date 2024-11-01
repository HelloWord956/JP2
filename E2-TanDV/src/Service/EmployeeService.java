package Service;

import Entity.Department;
import Entity.Employee;
import Entity.Gender;
import General.Generic;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeService implements Generic {
    private Set<Employee> employees;
    private Set<Department> departments;

    public EmployeeService(Set<Employee> employees, Set<Department> departments) {
        this.employees = employees;
        this.departments = departments;
    }

    @Override
    public Set<Employee> searchByName(String keyword) {
        Set<Employee> result = new HashSet<>();
        employees.stream()
                .filter(e -> e.getName().equalsIgnoreCase(keyword))
                .forEach(result::add);
        return result;
    }

    @Override
    public Map<String, Set<Employee>> groupByDepartment() {
        Map<String, Set<Employee>> groupedEmployees = new HashMap<>();
        departments.forEach(d -> {
            Set<Employee> employeesInDept = employees.stream()
                    .filter(emp -> emp.getDepartmentName().equals(d.getName()))
                    .collect(Collectors.toSet());
            groupedEmployees.put(d.getName(), employeesInDept);
        });
        return groupedEmployees;
    }


    @Override
    public Map<String, Integer> countTotal() {
        Map<String, Integer> countEmployee = new HashMap<>();
        departments.forEach(d -> {
            long totalEmp = employees.stream()
                    .filter(emp -> emp.getDepartmentName().equals(d.getName()))
                    .count();
            countEmployee.put(d.getName(), (int) totalEmp);
        });
        return countEmployee;
    }

    public Map<String, Long> countMaleEmployeesByDepartment() {
        Map<String, Long> maleEmployeeCounts = new HashMap<>();

        departments.forEach(department -> {
            long count = employees.stream()
                    .filter(emp -> emp.getDepartment().getId() == department.getId() && emp.getGender() == Gender.M)
                    .count();
            maleEmployeeCounts.put(department.getName(), count);
        });

        return maleEmployeeCounts;
    }

    public Map<String, Set<Employee>> findEmployeesWithBirthdaysThisMonth() {
        int currentMonth = LocalDateTime.now().getMonthValue();
        Map<String, Set<Employee>> birthdayEmployees = new HashMap<>();

        departments.forEach(department -> {
            Set<Employee> employeesInDept = employees.stream()
                    .filter(emp -> emp.getDepartment().getId() == department.getId() &&
                            emp.getDoB().getMonthValue() == currentMonth)
                    .collect(Collectors.toSet());
            birthdayEmployees.put(department.getName(), employeesInDept);
        });
        return birthdayEmployees;
    }
}
