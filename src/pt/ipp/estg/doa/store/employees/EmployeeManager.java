package pt.ipp.estg.doa.store.employees;

import pt.ipp.estg.doa.store.excpetion.ManagerValidationException;
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

    public void validate(Employee employee) throws ManagerValidationException {
        if (employee.getName() == null || employee.getName().isEmpty()) {
            throw new ManagerValidationException("Name is required");
        }
        if (employee.getNif() == null || employee.getNif().isEmpty()) {
            throw new ManagerValidationException("NIF is required");
        }
        if (employee.getHireDate() == null) {
            throw new ManagerValidationException("Hire Date is required");
        }
        if (employee.getSalary() <= 0) {
            throw new ManagerValidationException("Salary is required");
        }
        if (!employee.getNif().matches(ValidationUtil.FORMAT_9_DIGITS)) {
            throw new ManagerValidationException("NIF is not valid");
        }
        if (employee.getHireDate().isAfter(LocalDate.now())){
            throw new ManagerValidationException("Hire Date cannot be in the future");
        }
        if (employee instanceof SalesPerson) {
            double comission = ((SalesPerson) employee).getCommissionRate();
            if (comission <= 0 || comission > 100) {
                throw new ManagerValidationException("Commission ate must be between 0 and 100 (percentage)");
            }
        }
    }

}
