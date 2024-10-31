package Controller;

import Entity.Employee;
import Service.EmployeeService;

import java.util.Map;
import java.util.Set;

public class EmployeeController {
    private EmployeeService impService;
    public EmployeeController(EmployeeService impService) {
        this.impService = impService;
    }

    public Set<Employee> searchByName(String keyword) {
        return impService.searchByName(keyword);
    }

    public Map<String, Set<Employee>> groupByDepartment() {
        return impService.groupByDepartment();
    }

    public Map<String, Integer> countTotal() {
        return impService.countTotal();
    }
}
