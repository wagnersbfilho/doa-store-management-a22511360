package pt.ipp.estg.doa.store;

import pt.ipp.estg.doa.store.customers.Customer;
import pt.ipp.estg.doa.store.customers.CustomerManager;
import pt.ipp.estg.doa.store.dto.CustomerDTO;
import pt.ipp.estg.doa.store.excpetion.ManagerValidationException;

import java.util.Scanner;

public class MenuCustomer {

    public static boolean showMenuCustomer(Scanner scan) {
        int option_customer;
        System.out.println("--------------------------------");
        System.out.println("Choose an option for Customer!");
        System.out.println("--------------------------------");
        System.out.println("Option 1: List All");
        System.out.println("Option 2: Add");
        System.out.println("Option 3: Update contact information");
        System.out.println("Option 4: Update address");
        System.out.println("Option 5: Delete");
        System.out.println("Option 6: Find by ID");
        System.out.println("Option 7: Find by name");
        System.out.println("Option 8: Find by NIF");
        System.out.println("Option 9: Find by email");
        System.out.println("Option 10: Back to previous menu.");

        option_customer = scan.nextInt();
        scan.nextLine();
        try {
            CustomerManager customerManager = new CustomerManager();
            switch (option_customer) {
                case 1:
                    System.out.println("--- ALL CUSTOMER ---");
                    customerManager.findAll().stream().forEach(System.out::println);
                    return true;
                case 2:
                    System.out.println("--- ADD CUSTOMER ---");
                    System.out.println("Enter Name and Lastname: ");
                    String name = scan.nextLine();
                    System.out.println("Enter NIF: ");
                    String nif = scan.nextLine();
                    System.out.println("Enter Email: ");
                    String email = scan.nextLine();
                    System.out.println("Enter Address: ");
                    String address = scan.nextLine();
                    System.out.println("Enter Phone: ");
                    String phone = scan.nextLine();
                    customerManager.add(new Customer(name, nif, email, address, phone));
                    System.out.println("CUSTOMER SUCCESSFULLY ADDED!");
                    return true;
                case 3:
                    System.out.println("Enter ID Customer: ");
                    int id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter Customer Phone: ");
                    phone = scan.nextLine();
                    CustomerDTO customerDTO = new CustomerDTO();
                    customerDTO.setPhone(phone);
                    customerManager.update(id, customerDTO);
                    System.out.println("CUSTOMER SUCCESSFULLY ULPDATED!");
                    return true;
                case 4:
                    System.out.println("Enter ID Customer: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter Customer Address: ");
                    phone = scan.nextLine();
                    customerDTO = new CustomerDTO();
                    customerDTO.setAddress(phone);
                    customerManager.update(id, customerDTO);
                    System.out.println("CUSTOMER SUCCESSFULLY ULPDATED!");
                    return true;
                case 5:
                    System.out.println("--- DELETE CUSTOMER ---");
                    System.out.println("Enter ID Customer: ");
                    id = scan.nextInt();
                    customerManager.delete(id);
                    System.out.println("CUSTOMER SUCCESSFULLY DELETED!");
                    return true;
                case 6:
                    System.out.println("--- CUSTOMER BY ID ---");
                    System.out.println("Enter ID Customer: ");
                    id = scan.nextInt();
                    System.out.println(customerManager.findById(id));
                    return true;
                case 7:
                    System.out.println("--- CUSTOMER BY NAME ---");
                    System.out.println("Enter Customer Name: ");
                    name = scan.nextLine();
                    System.out.println(customerManager.findByName(name));
                    return true;
                case 8:
                    System.out.println("--- CUSTOMER BY NIF ---");
                    System.out.println("Enter Customer NIF: ");
                    name = scan.nextLine();
                    System.out.println(customerManager.findByNif(name));
                    return true;
                case 9:
                    System.out.println("--- CUSTOMER BY EMAIL ---");
                    System.out.println("Enter Customer Email: ");
                    name = scan.nextLine();
                    System.out.println(customerManager.findByEmail(name));
                    return true;
                case 10:
                    return true;
            }
        } catch (ManagerValidationException e) {
            System.out.println("Customer Error: " + e.getMessage());
        }
        return false;
    }
}
