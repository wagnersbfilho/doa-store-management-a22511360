package pt.ipp.estg.doa.store;

import pt.ipp.estg.doa.store.dto.PaymentDTO;
import pt.ipp.estg.doa.store.excpetion.ManagerValidationException;
import pt.ipp.estg.doa.store.orders.Order;
import pt.ipp.estg.doa.store.orders.OrderManager;
import pt.ipp.estg.doa.store.payments.Payment;
import pt.ipp.estg.doa.store.payments.PaymentManager;
import pt.ipp.estg.doa.store.payments.PaymentMethod;
import pt.ipp.estg.doa.store.utils.ValidationUtil;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuPayment {

    public static boolean showMenu(Scanner scan) {
        int option;
        System.out.println("--------------------------------");
        System.out.println("Choose an option for Payment!");
        System.out.println("--------------------------------");
        System.out.println("Option 1: List All");
        System.out.println("Option 2: Add");
        System.out.println("Option 3: Update payment method");
        System.out.println("Option 4: Delete");
        System.out.println("Option 5: Find by ID");
        System.out.println("Option 6: Find by Order");
        System.out.println("Option 7: Find by Payment method");
        System.out.println("Option 8: Find by Date Range");
        System.out.println("Option 9: Calculate total payments received");
        System.out.println("Option 10: Back to previous menu.");

        option = scan.nextInt();
        scan.nextLine();
        try {
            PaymentManager manager = new PaymentManager();
            OrderManager orderManager = new OrderManager();
            switch (option) {
                case 1:
                    System.out.println("--- ALL CUSTOMER ---");
                    manager.findAll().stream().forEach(System.out::println);
                    return true;
                case 2:
                    System.out.println("--- ADD PAYMENT TO THE ORDER ---");
                    System.out.println("Enter ID Order: ");
                    int id = scan.nextInt();
                    Order order = orderManager.findById(id);
                    System.out.println(order);
                    System.out.println("Enter Amount: ");
                    double amount = scan.nextDouble();
                    System.out.println("Enter Method (1 - CREDIT_CARD / 2 - BANK_TRANSFER / 3 - CASH): ");
                    int methodId = scan.nextInt();
                    PaymentMethod method = PaymentMethod.getPaymentMethod(methodId);
                    manager.add(new Payment(order, amount, method));
                    System.out.println("PAYMENT SUCCESSFULLY ADDED!");
                    return true;
                case 3:
                    System.out.println("Enter ID Payment: ");
                    id = scan.nextInt();
                    System.out.println("Enter Method (1 - CREDIT_CARD / 2 - BANK_TRANSFER / 3 - CASH): ");
                    methodId = scan.nextInt();
                    method = PaymentMethod.getPaymentMethod(methodId);
                    scan.nextLine();
                    PaymentDTO dto = new PaymentDTO();
                    dto.setPaymentMethod(method);
                    manager.update(id, dto);
                    System.out.println("PAYMENT METHOD SUCCESSFULLY ULPDATED!");
                    return true;
                case 4:
                    System.out.println("--- DELETE PAYMENT ---");
                    System.out.println("Enter ID Payment: ");
                    id = scan.nextInt();
                    manager.delete(id);
                    System.out.println("PAYMENT SUCCESSFULLY DELETED!");
                    return true;
                case 5:
                    System.out.println("--- PAYMENT BY ID ---");
                    System.out.println("Enter ID Payment: ");
                    id = scan.nextInt();
                    System.out.println(manager.findById(id));
                    return true;
                case 6:
                    System.out.println("--- PAYMENT BY ORDER ---");
                    System.out.println("Enter ID Order: ");
                    id = scan.nextInt();
                    System.out.println(manager.findByOrder(id));
                    return true;
                case 7:
                    System.out.println("--- PAYMENT BY METHOD ---");
                    System.out.println("Enter Method (1 - CREDIT_CARD / 2 - BANK_TRANSFER / 3 - CASH): ");
                    methodId = scan.nextInt();
                    method = PaymentMethod.getPaymentMethod(methodId);
                    System.out.println(manager.findByMethod(method));
                    return true;
                case 8:
                    System.out.println("--- PAYMENT BY DATA RANGE ---");
                    System.out.println("Enter Begin date (yyyy-MM-dd): ");
                    String begin = scan.nextLine();
                    System.out.println("Enter End date (yyyy-MM-dd): ");
                    String end = scan.nextLine();
                    System.out.println(manager.findByDataRange(
                            LocalDate.parse(begin, ValidationUtil.FORMAT_DATE), LocalDate.parse(end, ValidationUtil.FORMAT_DATE)));
                    return true;
                case 9:
                    System.out.println("Total Receiver: " + manager.calculateTotalReceived());
                    return true;
                case 10:
                    return true;
            }
        } catch (ManagerValidationException e) {
            System.out.println("Order Error: " + e.getMessage());
        }
        return false;
    }
}
