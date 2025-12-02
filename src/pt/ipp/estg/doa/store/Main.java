package pt.ipp.estg.doa.store;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static final int INIT = 0;
    public static final int CUSTOMER = 1;
    public static final int EMPLOYEE = 2;
    public static final int JEWELRY = 3;
    public static final int ORDER = 4;
    public static final int PAYMENT = 5;
    public static final int EXIT = 6;

    public static void main(String[] args) {
        int option = INIT;
        Scanner scan = new Scanner(System.in).useLocale(Locale.US);

        while (option != EXIT) {
            System.out.println("--------------------------------");
            System.out.println("Choose an menu option!");
            System.out.println("--------------------------------");
            System.out.println("Option 1: Customer");
            System.out.println("Option 2: Employee");
            System.out.println("Option 3: Jewelry");
            System.out.println("Option 4: Order");
            System.out.println("Option 5: Payment");
            System.out.println("Option 6: Exit");
            System.out.println("Your choice: ");

            try {
                option = scan.nextInt();

                switch (option) {
                    case CUSTOMER:
                        if (MenuCustomer.showMenu(scan)) continue;
                    case EMPLOYEE:
                        if (MenuEmployee.showMenu(scan)) continue;
                    case JEWELRY:
                        break;
                    case ORDER:
                        break;
                    case PAYMENT:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("The choice must be a number. Try again! ");
                scan.nextLine();
                continue;
            }
        }
    }


}
