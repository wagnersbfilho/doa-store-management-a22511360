package pt.ipp.estg.doa.store.employees;

import pt.ipp.estg.doa.store.utils.AbstractManager;
import pt.ipp.estg.doa.store.utils.CSVUtilEmployee;
import pt.ipp.estg.doa.store.utils.ValidationUtil;

import java.time.LocalDate;
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

    public boolean validate(Employee employee) {
        if (employee.getName() == null || employee.getName().isEmpty()) {
            System.out.println("Name is required");
            return false;
        }
        if (employee.getNif() == null || employee.getNif().isEmpty()) {
            System.out.println("NIF is required");
            return false;
        }
        if (employee.getHireDate() == null) {
            System.out.println("Hire Date is required");
            return false;
        }
        if (employee.getSalary() <= 0) {
            System.out.println("Salary is required");
            return false;
        }
        if (!employee.getNif().matches(ValidationUtil.FORMAT_9_DIGITS)) {
            System.out.println("NIF is not valid");
            return false;
        }
        if (employee.getHireDate().isAfter(LocalDate.now())){
            System.out.println("Hire Date cannot be in the future");
            return false;
        }

        return true;
    }

}
