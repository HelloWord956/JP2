package General;

import Entity.Employee;

import java.util.Map;
import java.util.Set;

public interface Generic<T> {
    Set<T> searchByName(String keyword);
    Map<String, Set<T>> groupByDepartment();
    Map<String, Integer> countTotal();
}
