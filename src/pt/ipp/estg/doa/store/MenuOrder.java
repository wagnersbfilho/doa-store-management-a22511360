package pt.ipp.estg.doa.store;

import pt.ipp.estg.doa.store.customers.Customer;
import pt.ipp.estg.doa.store.customers.CustomerManager;
import pt.ipp.estg.doa.store.excpetion.ManagerValidationException;
import pt.ipp.estg.doa.store.jewelry.Jewelry;
import pt.ipp.estg.doa.store.jewelry.JewelryManager;
import pt.ipp.estg.doa.store.orders.*;
import pt.ipp.estg.doa.store.utils.ValidationUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuOrder {

    public static boolean showMenu(Scanner scan) {
        int option;
        System.out.println("--------------------------------");
        System.out.println("Choose an option for Order!");
        System.out.println("--------------------------------");
        System.out.println("Option 1: List All");
        System.out.println("Option 2: Add");
        System.out.println("Option 3: Update Status");
        System.out.println("Option 4: Delete");
        System.out.println("Option 5: Find by ID");
        System.out.println("Option 6: Find by Customer");
        System.out.println("Option 7: Find by Status");
        System.out.println("Option 8: Find by Data Range");
        System.out.println("Option 9: Calculate total revenue.");
        System.out.println("Option 10: Back to previous menu.");

        option = scan.nextInt();
        scan.nextLine();
        try {
            OrderManager orderManager = new OrderManager();
            OrderItemManager orderItemManager = new OrderItemManager();
            JewelryManager jewelryManager = new JewelryManager();
            CustomerManager customerManager = new CustomerManager();
            switch (option) {
                case 1:
                    System.out.println("--- ALL ORDERS ---");
                    orderManager.findAll().stream().forEach(System.out::println);
                    return true;
                case 2:
                    System.out.println("---Create new ORDER for a customer ---");
                    System.out.println("Enter ID Customer: ");
                    int cutomerID = scan.nextInt();
                    scan.nextLine();
                    Customer customer = customerManager.findById(cutomerID);
                    System.out.println(customer);

                    System.out.println("\n--Add jewelry with quantities--");
                    List<OrderItem> items = new ArrayList<>();
                    String exit = "INIT";
                    while (!exit.equalsIgnoreCase("EXIT")) {
                        System.out.println("\nAdd a new jewelry to Order? Type ADD or EXIT: ");
                        exit = scan.nextLine();
                        if (!exit.equalsIgnoreCase("EXIT")) {
                            System.out.println("Enter ID Jewelry: ");
                            int id = scan.nextInt();
                            Jewelry jewelry = jewelryManager.findById(id);
                            System.out.println(jewelry);
                            if (jewelry.getStock() > 0) {
                                System.out.println("Enter Quantity: ");
                                int quantity = scan.nextInt();
                                scan.nextLine();

                                System.out.println("\nAdd the jewelry above to the Order? Type YES or NO: ");
                                exit = scan.nextLine();
                                if (exit.equalsIgnoreCase("YES")) {
                                    items.add(orderItemManager.add(new OrderItem(jewelry, quantity)));
                                }
                            } else {
                                System.out.println("Item not available in Stock!");
                            }
                        }
                    }
                    if (!items.isEmpty()) {
                        orderManager.add(new Order(customer, items));
                        System.out.println("ORDER SUCCESSFULLY ADDED!");
                    }
                    return true;
                case 3:
                    System.out.println("Enter ID Order: ");
                    int id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter new Status (2 - ACCEPTED / 3 - DELIVERED / 4 - CANCELED): ");
                    int status = scan.nextInt();
                    OrderStatus orderStatus = OrderStatus.getOrderStatus(status);
                    orderManager.updateStatus(id, orderStatus);
                    System.out.println("ORDER SUCCESSFULLY ULPDATED!");
                    return true;
                case 4:
                    System.out.println("--- DELETE CUSTOMER ---");
                    System.out.println("Enter ID Order: ");
                    id = scan.nextInt();
                    orderManager.deleteOrder(id);
                    System.out.println("ORDER SUCCESSFULLY DELETED!");
                    return true;
                case 5:
                    System.out.println("--- ORDER BY ID ---");
                    System.out.println("Enter ID Order: ");
                    id = scan.nextInt();
                    System.out.println(orderManager.findById(id));
                    return true;
                case 6:
                    System.out.println("--- CUSTOMER BY CUSTOMER ---");
                    System.out.println("Enter Customer ID: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    System.out.println(orderManager.findByCustomer(id));
                    return true;
                case 7:
                    System.out.println("--- CUSTOMER BY STATUS ---");
                    System.out.println("Enter Status (1 - PENDING / 2 - ACCEPTED / 3 - DELIVERED / 4 - CANCELED): ");
                    status = scan.nextInt();
                    orderStatus = OrderStatus.getOrderStatus(status);
                    scan.nextLine();
                    System.out.println(orderManager.findByStatus(orderStatus));
                    return true;
                case 8:
                    System.out.println("--- CUSTOMER BY DATA RANGE ---");
                    System.out.println("Enter Begin date (yyyy-MM-dd): ");
                    String begin = scan.nextLine();
                    System.out.println("Enter End date (yyyy-MM-dd): ");
                    String end = scan.nextLine();
                    System.out.println(orderManager.findByDataRange(
                            LocalDate.parse(begin, ValidationUtil.FORMAT_DATE), LocalDate.parse(end, ValidationUtil.FORMAT_DATE)));
                    return true;
                case 9:
                    System.out.println("Total Revenue: " + orderManager.calculateTotalRevenue());
                    return true;
                case 10:
                    return true;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Order Invalid Date: " + e.getMessage());
        } catch (ManagerValidationException e) {
            System.out.println("Order Error: " + e.getMessage());
        }
        return false;
    }
}
