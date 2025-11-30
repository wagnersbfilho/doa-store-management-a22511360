package pt.ipp.estg.doa.store.employees;

import pt.ipp.estg.doa.store.utils.AbstractManager;
import pt.ipp.estg.doa.store.utils.CSVUtilEmployee;

import java.util.List;

public class EmployeeManager extends AbstractManager<Employee> {

    public EmployeeManager() {
        super(new CSVUtilEmployee());
    }

    public List<Employee> findByName(String name) {
        List<Employee> employees = findAll();
        return employees.stream()
                .filter(employee -> employee.getName().toUpperCase().contains(name.toUpperCase()))
                .toList();
    }

    public List<Employee> findByType(EmployeeType type) {
        List<Employee> employees = findAll();
        return employees.stream()
                .filter(employee -> employee.getType().equals(type))
                .toList();
    }

    public double calculateTotalPayroll() {
        List<Employee> employees = findAll();
        return employees.stream().mapToDouble(Employee::getSalary).sum();
    }

}
