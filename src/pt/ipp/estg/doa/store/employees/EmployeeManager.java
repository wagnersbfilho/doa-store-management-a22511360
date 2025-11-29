package pt.ipp.estg.doa.store.employees;

import pt.ipp.estg.doa.store.utils.CSVUtil;

import java.util.List;

public class EmployeeManager {

    CSVUtil repository;

    public EmployeeManager() {
        this.repository = new CSVUtil();
    }

    public List<Employee> findAll() {
        return this.repository.loadEmployeeData();
    }

    public Employee findById(int id) {
        List<Employee> employees = findAll();
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Employee> findByName(String name) {
        List<Employee> employees = findAll();
        return employees.stream()
                .filter(employee -> employee.getName().toUpperCase().contains(name.toUpperCase()))
                .toList();
    }

    public List<Employee> findByType(EmployeeType type  ) {
        List<Employee> employees = findAll();
        return employees.stream()
                .filter(employee -> employee.getType().equals(type))
                .toList();
    }

    public double calculateTotalPayroll() {
        List<Employee> employees = findAll();
        return employees.stream().mapToDouble(Employee::getSalary).sum();
    }

    public void updateEmployeeSalary(Employee employee, double newSalary) {
        List<Employee> employees = findAll();
        employees.stream()
                .filter(employeeUpdate -> employeeUpdate.getId() == employee.getId())
                .findFirst()
                .ifPresent(employeeUpdate -> employeeUpdate.setSalary(newSalary));
        this.repository.updateEmployeeData(employees);
    }

    public void updateSalesPersonComission(Employee employee, double newComission) {
        if (employee instanceof SalesPerson) {
            List<Employee> salesPersons = findAll();
            salesPersons.stream()
                    .filter(employeeUpdate -> employeeUpdate.getId() == employee.getId())
                    .findFirst()
                    .ifPresent(employeeUpdate -> ((SalesPerson)employeeUpdate).setCommissionRate(newComission));
            this.repository.updateEmployeeData(salesPersons);

        } else {
            System.out.println("Este tipo de Employee nao possui comissao para atualiar: " + employee.getType().name());
        }
    }

    public void updateManagerBonus(Employee employee, double newBonus) {
        if (employee instanceof Manager) {
            List<Employee> salesPersons = findAll();
            salesPersons.stream()
                    .filter(employeeUpdate -> employeeUpdate.getId() == employee.getId())
                    .findFirst()
                    .ifPresent(employeeUpdate -> ((Manager)employeeUpdate).setBonus(newBonus));
            this.repository.updateEmployeeData(salesPersons);

        } else {
            System.out.println("Este tipo de Employee nao possui comissao para atualiar: " + employee.getType().name());
        }
    }
}
