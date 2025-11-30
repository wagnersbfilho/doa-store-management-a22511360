package pt.ipp.estg.doa.store;

import pt.ipp.estg.doa.store.customers.Customer;
import pt.ipp.estg.doa.store.customers.CustomerManager;
import pt.ipp.estg.doa.store.dto.CustomerDTO;
import pt.ipp.estg.doa.store.dto.EmployeeDTO;
import pt.ipp.estg.doa.store.dto.JewelryDTO;
import pt.ipp.estg.doa.store.employees.*;
import pt.ipp.estg.doa.store.jewelry.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        EmployeeManager employeeManager = new EmployeeManager();
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
        EmployeeDTO dto = new EmployeeDTO();
        dto.setSalary(4321.00);
        employeeManager.update(1, dto);
        System.out.println("--- UPDATE SALES PERSON COMISSION ---");
        dto = new EmployeeDTO();
        dto.setCommissionRate(50.50);
        employeeManager.update(2, dto);
        System.out.println("--- UPDATE MANAGER BONUS ---");
        dto = new EmployeeDTO();
        dto.setBonus(1234.00);
        employeeManager.update(2, dto);
        System.out.println("--- DELETE EMPLOYEE ---");
        employeeManager.delete(3);
        employeeManager.delete(4);
        System.out.println("--- ALL EMPLOYEES UPDATED ---");
        employeeManager.findAll().stream().forEach(System.out::println);


        JewelryManager jewelryManager = new JewelryManager();
        System.out.println("\n\n***************** JEWELRY *******************");
        System.out.println("--- ADD EARING ---");
        jewelryManager.add(new Earring(0, "Silver Chain", JewelryType.EARRING, "Silver",
                12.3, 23.4, 12, Category.CASUAL, "Stud"));
        System.out.println("--- ADD NECKLANE ---");
        jewelryManager.add(new Necklace(0, "Silver Chain", JewelryType.NECKLACE, "Silver",
                33.3, 33.4, 33, Category.LUXURY, 33.0));
        System.out.println("--- ADD RING ---");
        jewelryManager.add(new Ring(0, "Silver Chain", JewelryType.RING, "Silver",
                20.3, 20.4, 33, Category.BRIDAL, 20));
        System.out.println("--- ALL JEWELRY ---");
        jewelryManager.findAll().stream().forEach(System.out::println);
        System.out.println("--- JEWELRY BY ID ---");
        System.out.println(jewelryManager.findById(1));
        /*System.out.println("--- JEWELRY BY NAME ---");
        jewelryManager.findByName("santos").forEach(System.out::println);
        System.out.println("--- JEWELRY BY TYPE ---");
        jewelryManager.findByType(EmployeeType.MANAGER).forEach(System.out::println);*/
        System.out.println("--- UPDATE JEWELRY ---");
        JewelryDTO jewelryDTO = new JewelryDTO();
        jewelryDTO.setMaterial("Gold");
        jewelryManager.update(4, jewelryDTO);
        System.out.println("--- DELETE JEWELRY ---");
        jewelryManager.delete(5);
        System.out.println("--- ALL JEWELRIES UPDATED ---");
        jewelryManager.findAll().stream().forEach(System.out::println);


        CustomerManager customerManager = new CustomerManager();
        System.out.println("\n\n***************** CUSTOMER *******************");
        System.out.println("--- ADD CUSTOMER ---");
        customerManager.add(new Customer(0, "Wagner Filho", "999999999", "wagner@email.com", "Lisboa", "9111111"));
        System.out.println("--- ALL CUSTOMER ---");
        customerManager.findAll().stream().forEach(System.out::println);
        System.out.println("--- CUSTOMER BY ID ---");
        System.out.println(customerManager.findById(1));
        System.out.println("--- UPDATE CUSTOMER ---");
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Ana Costa da Silga");
        customerManager.update(1, customerDTO);
        System.out.println("--- DELETE CUSTOMER ---");
        customerManager.delete(2);
        System.out.println("--- ALL CUSTOMERS UPDATED ---");
        customerManager.findAll().stream().forEach(System.out::println);
    }
}
