package pt.ipp.estg.doa.store;

import pt.ipp.estg.doa.store.dto.EmployeeDTO;
import pt.ipp.estg.doa.store.dto.JewelryDTO;
import pt.ipp.estg.doa.store.employees.EmployeeManager;
import pt.ipp.estg.doa.store.employees.EmployeeType;
import pt.ipp.estg.doa.store.employees.Manager;
import pt.ipp.estg.doa.store.employees.SalesPerson;
import pt.ipp.estg.doa.store.excpetion.ManagerValidationException;
import pt.ipp.estg.doa.store.jewelry.*;
import pt.ipp.estg.doa.store.utils.ValidationUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuJewelry {

    public static boolean showMenu(Scanner scan) {
        int option;
        System.out.println("--------------------------------");
        System.out.println("Choose an option for Jewelry!");
        System.out.println("--------------------------------");
        System.out.println("Option 1: List All");
        System.out.println("Option 2: Add Earring ");
        System.out.println("Option 3: Add Necklace");
        System.out.println("Option 4: Add Ring");
        System.out.println("Option 5: Update Price");
        System.out.println("Option 6: Update Stock quantity");
        System.out.println("Option 7: Update Details (material and weight)");
        System.out.println("Option 8: Delete");
        System.out.println("Option 9: Find by ID");
        System.out.println("Option 10: Find by name");
        System.out.println("Option 11: Find by material");
        System.out.println("Option 12: Find By Type (1 - Earring / 2 - Necklace / 3 - Ring)");
        System.out.println("Option 13: Find By Category (1 - Luxury / 2 - Casual / 3 - Bridal)");
        System.out.println("Option 14: Find in Stock");
        System.out.println("Option 15: Find low stock items.");
        System.out.println("Option 16: Back to previous menu.");

        option = scan.nextInt();
        scan.nextLine();
        try {
            JewelryManager manager = new JewelryManager();
            switch (option) {
                case 1:
                    System.out.println("--- ALL JEWELRY ---");
                    manager.findAll().stream().forEach(System.out::println);
                    return true;
                case 2:
                    System.out.println("--- ADD EARRING ---");
                    System.out.println("Enter Name: ");
                    String name = scan.nextLine();
                    System.out.println("Enter material: ");
                    String material = scan.nextLine();
                    System.out.println("Enter Weight: ");
                    double weight = scan.nextDouble();
                    System.out.println("Enter Price: ");
                    double price = scan.nextDouble();
                    System.out.println("Enter Stock: ");
                    int stock = scan.nextInt();
                    System.out.println("Enter Category (1 - Luxury / 2 - Casual / 3 - Bridal):");
                    int categoryID = scan.nextInt();
                    scan.nextLine();
                    Category category = categoryID == 1 ? Category.LUXURY : categoryID == 2 ? Category.CASUAL : categoryID == 3 ? Category.BRIDAL : null;
                    System.out.println("Enter Clasptype: ");
                    String clastype = scan.nextLine();
                    manager.add(new Earring(name, JewelryType.EARRING, material, weight, price, stock, category, clastype));
                    System.out.println("EARRING SUCCESSFULLY ADDED!");
                    return true;
                case 3:
                    System.out.println("--- ADD NECKLACE ---");
                    System.out.println("Enter Name: ");
                    name = scan.nextLine();
                    System.out.println("Enter material: ");
                    material = scan.nextLine();
                    System.out.println("Enter Weight: ");
                    weight = scan.nextDouble();
                    System.out.println("Enter Price: ");
                    price = scan.nextDouble();
                    System.out.println("Enter Stock: ");
                    stock = scan.nextInt();
                    System.out.println("Enter Category (1 - Luxury / 2 - Casual / 3 - Bridal):");
                    categoryID = scan.nextInt();
                    scan.nextLine();
                    category = categoryID == 1 ? Category.LUXURY : categoryID == 2 ? Category.CASUAL : categoryID == 3 ? Category.BRIDAL : null;
                    System.out.println("Enter Length: ");
                    int length = scan.nextInt();
                    scan.nextLine();
                    manager.add(new Necklace(name, JewelryType.NECKLACE, material, weight, price, stock, category, length));
                    System.out.println("NECKLANE SUCCESSFULLY ADDED!");
                    return true;
                case 4:
                    System.out.println("--- ADD RING ---");
                    System.out.println("Enter Name: ");
                    name = scan.nextLine();
                    System.out.println("Enter Material: ");
                    material = scan.nextLine();
                    System.out.println("Enter Weight: ");
                    weight = scan.nextDouble();
                    System.out.println("Enter Price: ");
                    price = scan.nextDouble();
                    System.out.println("Enter Stock: ");
                    stock = scan.nextInt();
                    System.out.println("Enter Category (1 - Luxury / 2 - Casual / 3 - Bridal):");
                    categoryID = scan.nextInt();
                    scan.nextLine();
                    category = categoryID == 1 ? Category.LUXURY : categoryID == 2 ? Category.CASUAL : categoryID == 3 ? Category.BRIDAL : null;
                    System.out.println("Enter Length: ");
                    int size = scan.nextInt();
                    scan.nextLine();
                    manager.add(new Ring(name, JewelryType.RING, material, weight, price, stock, category, size));
                    System.out.println("RING SUCCESSFULLY ADDED!");
                    return true;
                case 5:
                    System.out.println("Enter ID Jewelry: ");
                    int id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter new Price: ");
                    price = scan.nextDouble();
                    JewelryDTO dto = new JewelryDTO();
                    dto.setPrice(price);
                    manager.update(id, dto);
                    System.out.println("JEWELRY SUCCESSFULLY ULPDATED!");
                    return true;
                case 6:
                    System.out.println("Enter ID Jewelry: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter new Stock quantity: ");
                    stock = scan.nextInt();
                    dto = new JewelryDTO();
                    dto.setStock(stock);
                    manager.update(id, dto);
                    System.out.println("JEWELRY SUCCESSFULLY ULPDATED!");
                    return true;
                case 7:
                    System.out.println("Enter ID Jewelry: ");
                    id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter new Details (material and weight): ");
                    System.out.println("Material: ");
                    material = scan.nextLine();
                    System.out.println("Weight: ");
                    weight = scan.nextDouble();
                    dto = new JewelryDTO();
                    dto.setMaterial(material);
                    dto.setWeight(weight);
                    manager.update(id, dto);
                    System.out.println("JEWELRY SUCCESSFULLY ULPDATED!");
                    return true;
                case 8:
                    System.out.println("--- DELETE JEWELRY ---");
                    System.out.println("Enter ID Jewelry: ");
                    id = scan.nextInt();
                    manager.delete(id);
                    System.out.println("JEWELRY SUCCESSFULLY DELETED!");
                    return true;
                case 9:
                    System.out.println("--- JEWELRY BY ID ---");
                    System.out.println("Enter ID Jewelry: ");
                    id = scan.nextInt();
                    System.out.println(manager.findById(id));
                    return true;
                case 10:
                    System.out.println("--- JEWELRY BY NAME ---");
                    System.out.println("Enter Jewelry Name: ");
                    name = scan.nextLine();
                    System.out.println(manager.findByName(name));
                    return true;
                case 11:
                    System.out.println("--- JEWELRY BY MATERIAL ---");
                    System.out.println("Enter Jewelry material: ");
                    name = scan.nextLine();
                    System.out.println(manager.findByMaterial(name));
                    return true;
                case 12:
                    System.out.println("Enter Jewelry Type (1 - Earring / 2 - Necklace / 3 - Ring): ");
                    int typeID = scan.nextInt();
                    JewelryType type = typeID == 1 ? JewelryType.EARRING : typeID == 2 ? JewelryType.NECKLACE :  typeID == 3 ? JewelryType.RING : null;
                    System.out.println(manager.findByType(type));
                    return true;
                case 13:
                    System.out.println("Enter Jewelry Category (1 - Luxury / 2 - Casual / 3 - Bridal)");
                    categoryID = scan.nextInt();
                    category = categoryID == 1 ? Category.LUXURY : categoryID == 2 ? Category.CASUAL : categoryID == 3 ? Category.BRIDAL : null;
                    System.out.println(manager.findByCategory(category));
                    return true;
                case 14:
                    System.out.println("Enter Jewelry Name: ");
                    name = scan.nextLine();
                    System.out.println("Is in Stock: " + manager.isInStock(name));
                    return true;
                case 15:
                    System.out.println("--- LOW STOCK ITEMS ---");
                    System.out.println(manager.findLowStock());
                    return true;
                case 16:
                    return true;
            }
        } catch (ManagerValidationException e) {
            System.out.println("Jewelry Error: " + e.getMessage());
        }
        return false;
    }
}
