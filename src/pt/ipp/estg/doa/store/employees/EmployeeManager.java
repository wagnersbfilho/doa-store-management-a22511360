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

    public void add (SalesPerson salesPerson) {
        List<Employee> employees = findAll();
        salesPerson.setId(employees.size() +1);
        employees.add(salesPerson);
        this.repository.updateEmployeeData(employees);
    }

    public void add (Manager manager) {
        List<Employee> employees = findAll();
        manager.setId(employees.size() +1);
        employees.add(manager);
        this.repository.updateEmployeeData(employees);
    }

    public void delete (int id) {
        List<Employee> employees = findAll();
        Employee employee = employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Employee nao encontrado."));

        //TODO Validation: Cannot delete if employee has associated orders (for salespeople)
        //if (employee instanceof SalesPerson sp && sp.hasOrders()) {
        //    throw new IllegalStateException("SalesPerson esta associado a um Ordem e nao pode ser apagado.");
        //}

        employees.remove(employee);
        this.repository.updateEmployeeData(employees);
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
