package pt.ipp.estg.doa.store;

import pt.ipp.estg.doa.store.employees.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");        EmployeeManager employeeManager = new EmployeeManager();
        System.out.println("***************** EMPLOYEE *******************");
        System.out.println("--- ADD SALES PERSON ---");
        employeeManager.add(new SalesPerson(0, "Paulo Santos", "123456789",
                LocalDate.parse("2024-01-01", FORMATTER), 1500.00, 6.0, 10000.00));
        System.out.println("--- ADD MANAGER ---");
        employeeManager.add(new Manager(0, "Daniel Silva", "223344556",
                LocalDate.parse("2022-02-02", FORMATTER), 1500.00, "Sales", 3000.00));
        System.out.println("--- ALL EMPLOYEES ---");
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
        System.out.println("--- DELETE EMPLOYEE ---");
        employeeManager.delete(3);
        employeeManager.delete(4);
        System.out.println("--- ALL EMPLOYEES UPDATED ---");
        employeeManager.findAll().stream().forEach(System.out::println);

    }
}
