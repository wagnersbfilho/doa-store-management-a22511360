package pt.ipp.estg.doa.store;

import pt.ipp.estg.doa.store.dto.EmployeeDTO;
import pt.ipp.estg.doa.store.employees.EmployeeManager;
import pt.ipp.estg.doa.store.employees.EmployeeType;
import pt.ipp.estg.doa.store.employees.Manager;
import pt.ipp.estg.doa.store.employees.SalesPerson;
import pt.ipp.estg.doa.store.excpetion.ManagerValidationException;
import pt.ipp.estg.doa.store.utils.ValidationUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuEmployee {

    public static boolean showMenu(Scanner scan) {
        int option;
        System.out.println("--------------------------------");
        System.out.println("Choose an option for Employee!");
        System.out.println("--------------------------------");
        System.out.println("Option 1: List All");
        System.out.println("Option 2: Add Sales Person");
        System.out.println("Option 3: Add Manager");
        System.out.println("Option 4: Update salary");
        System.out.println("Option 5: Update Sales Person commission rate");
        System.out.println("Option 6: Update Manager bonus");
        System.out.println("Option 7: Delete");
        System.out.println("Option 8: Find by ID");
        System.out.println("Option 9: Find by name");
        System.out.println("Option 10: Find All Salesperson");
        System.out.println("Option 11: Find All Manager");
        System.out.println("Option 12: Calculate total payroll (sum of all salaries)");
        System.out.println("Option 13: Back to previous menu.");

        option = scan.nextInt();
        scan.nextLine();
        try {
            EmployeeManager manager = new EmployeeManager();
            switch (option) {
                case 1:
                    System.out.println("--- ALL EMPLOYEES ---");
                    manager.findAll().stream().forEach(System.out::println);
                    return true;
                case 2:
                    System.out.println("--- ADD SALES PERSON ---");
                    System.out.println("Enter Name and Lastname: ");
                    String name = scan.nextLine();
                    System.out.println("Enter NIF: ");
                    String nif = scan.nextLine();
                    System.out.println("Enter Hire date (yyyy-MM-dd): ");
                    String date = scan.nextLine();
                    System.out.println("Enter Salary: ");
                    double salary = scan.nextDouble();
                    System.out.println("Enter Comission Rate: ");
                    double comission = scan.nextDouble();
                    System.out.println("Enter Total Sales: ");
                    double sales = scan.nextDouble();
                    manager.add(new SalesPerson(name, nif, LocalDate.parse(date, ValidationUtil.FORMAT_DATE), salary, comission, sales));
                    System.out.println("SALES PERSON SUCCESSFULLY ADDED!");
                    return true;
                case 3:
                    System.out.println("--- ADD MANAGER ---");
                    System.out.println("Enter Name and Lastname: ");
                    name = scan.nextLine();
                    System.out.println("Enter NIF: ");
                    nif = scan.nextLine();
                    System.out.println("Enter Hire date (yyyy-MM-dd): ");
                    date = scan.nextLine();
                    System.out.println("Enter Salary: ");
                    salary = scan.nextDouble();
                    scan.nextLine();
                    System.out.println("Enter Department: ");
                    String depatarment = scan.nextLine();
                    System.out.println("Enter Bonus value: ");
                    double bonus = scan.nextDouble();
                    manager.add(new Manager(name, nif, LocalDate.parse(date, ValidationUtil.FORMAT_DATE), salary, depatarment, bonus));
                    System.out.println("MANAGER SUCCESSFULLY ADDED!");
                    return true;
                case 4:
                    System.out.println("Enter ID Employee: ");
                    int id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter new Salary: ");
                    salary = scan.nextDouble();
                    EmployeeDTO dto = new EmployeeDTO();
                    dto.setSalary(salary);
                    manager.update(id, dto);
                    System.out.println("EMPLOYEE SUCCESSFULLY ULPDATED!");
                    return true;
                case 5:
                    System.out.println("Enter ID Sales Person: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter new Comission rate: ");
                    comission = scan.nextDouble();
                    dto = new EmployeeDTO();
                    dto.setCommissionRate(comission);
                    manager.update(id, dto);
                    System.out.println("SALES PERSON SUCCESSFULLY ULPDATED!");
                    return true;
                case 6:
                    System.out.println("Enter ID Manager: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter new Bonus value: ");
                    bonus = scan.nextDouble();
                    dto = new EmployeeDTO();
                    dto.setBonus(bonus);
                    manager.update(id, dto);
                    System.out.println("MANAGER SUCCESSFULLY ULPDATED!");
                    return true;
                case 7:
                    System.out.println("--- DELETE EMPLOYEE ---");
                    System.out.println("Enter ID Employee: ");
                    id = scan.nextInt();
                    manager.delete(id);
                    System.out.println("EMPLOYEE SUCCESSFULLY DELETED!");
                    return true;
                case 8:
                    System.out.println("--- EMPLOYEE BY ID ---");
                    System.out.println("Enter ID Employee: ");
                    id = scan.nextInt();
                    System.out.println(manager.findById(id));
                    return true;
                case 9:
                    System.out.println("--- EMPLOYEE BY NAME ---");
                    System.out.println("Enter Employee Name: ");
                    name = scan.nextLine();
                    System.out.println(manager.findByName(name));
                    return true;
                case 10:
                    System.out.println("--- ALL SALES PERSON ---");
                    manager.findByType(EmployeeType.SALESPERSON).forEach(System.out::println);
                    return true;
                case 11:
                    System.out.println("--- ALL MANAGERS ---");
                    manager.findByType(EmployeeType.MANAGER).forEach(System.out::println);
                    return true;
                case 12:
                    System.out.println("--- TOTAL PAYROLL ---");
                    System.out.println(manager.calculateTotalPayroll());
                    return true;
                case 13:
                    return true;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Employee Invalid Date: " + e.getMessage());
        } catch (ManagerValidationException e) {
            System.out.println("Employee Error: " + e.getMessage());
        }
        return false;
    }
}
