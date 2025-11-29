package pt.ipp.estg.doa.store;

import pt.ipp.estg.doa.store.employees.Employee;
import pt.ipp.estg.doa.store.employees.EmployeeManager;
import pt.ipp.estg.doa.store.employees.EmployeeType;

public class Main {

    public static void main(String[] args) {

        System.out.println("***************** EMPLOYEE *******************");
        System.out.println("--- ALL EMPLOYEES ---");
        EmployeeManager employeeManager = new EmployeeManager();
        employeeManager.findAll().stream().forEach(System.out::println);
        System.out.println("--- EMPLOYEES BY ID ---");
        System.out.println(employeeManager.findById(1));
        System.out.println("--- EMPLOYEES BY NAME ---");
        employeeManager.findByName("santos").forEach(System.out::println);
        System.out.println("--- EMPLOYEES BY TYPE ---");
        employeeManager.findByType(EmployeeType.MANAGER).forEach(System.out::println);
        System.out.println("--- TOTAL PAYROLL ---");
        System.out.println(employeeManager.calculateTotalPayroll());
        System.out.println("--- UPDATE SALARY ---");
        Employee employee = employeeManager.findById(1);
        employeeManager.updateEmployeeSalary(employee, 5000.00);
        System.out.println("--- UPDATE SALES PERSON COMISSION ---");
        Employee employee2 = employeeManager.findById(2);
        employeeManager.updateSalesPersonComission(employee2, 10.50);
        System.out.println("--- UPDATE MANAGER BONUS ---");
        Employee employee3 = employeeManager.findById(2);
        employeeManager.updateManagerBonus(employee3, 4000.00);
        System.out.println("--- ALL EMPLOYEES UPDATED ---");
        employeeManager.findAll().stream().forEach(System.out::println);

    }
}
