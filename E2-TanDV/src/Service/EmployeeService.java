package Service;

import Entity.Employee;
import General.Generic;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeService implements Generic {
    private Set<Employee> employees;

    public EmployeeService(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public Set<Employee> searchByName(String keyword) {
        return employees.stream()
                .filter(e -> e.getName().equalsIgnoreCase(keyword))
                .collect(Collectors.toSet());
    }

    @Override
    public Map<String, Set<Employee>> groupByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentName, Collectors.toSet()));
    }


    @Override
    public Map<String, Integer> countTotal() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentName, Collectors.summingInt(e -> 1)));
    }
}
