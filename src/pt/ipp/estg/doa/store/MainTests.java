package pt.ipp.estg.doa.store;

import pt.ipp.estg.doa.store.customers.Customer;
import pt.ipp.estg.doa.store.customers.CustomerManager;
import pt.ipp.estg.doa.store.dto.*;
import pt.ipp.estg.doa.store.employees.EmployeeManager;
import pt.ipp.estg.doa.store.employees.EmployeeType;
import pt.ipp.estg.doa.store.employees.Manager;
import pt.ipp.estg.doa.store.employees.SalesPerson;
import pt.ipp.estg.doa.store.excpetion.ManagerValidationException;
import pt.ipp.estg.doa.store.jewelry.*;
import pt.ipp.estg.doa.store.orders.Order;
import pt.ipp.estg.doa.store.orders.OrderItem;
import pt.ipp.estg.doa.store.orders.OrderItemManager;
import pt.ipp.estg.doa.store.orders.OrderManager;
import pt.ipp.estg.doa.store.payments.Payment;
import pt.ipp.estg.doa.store.payments.PaymentManager;
import pt.ipp.estg.doa.store.payments.PaymentMethod;
import pt.ipp.estg.doa.store.utils.ValidationUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainTests {

    public static void main(String[] args) {

        try {
            EmployeeManager employeeManager = new EmployeeManager();
            System.out.println("***************** EMPLOYEE *******************");
            System.out.println("--- ADD SALES PERSON ---");
            employeeManager.add(new SalesPerson(0, "Paulo Santos", "123456789",
                    LocalDate.parse("2024-01-01", ValidationUtil.FORMAT_DATE), 1500.00, 6.0, 10000.00));
            System.out.println("--- ADD MANAGER ---");
            employeeManager.add(new Manager(0, "Daniel Silva", "223344556",
                    LocalDate.parse("2022-02-02", ValidationUtil.FORMAT_DATE), 1500.00, "Sales", 3000.00));
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
            employeeManager.update(1, dto);
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
            Jewelry ring = jewelryManager.add(new Earring(0, "Silver Chain", JewelryType.EARRING, "Silver",
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
            customerManager.add(new Customer("Wagner Filho", "999999999", "wagner@email.com", "Lisboa", "911111189"));
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


            OrderItemManager orderItemManager = new OrderItemManager();
            System.out.println("\n\n***************** ORDER ITEM *******************");
            System.out.println("--- ADD ORDER ITEM ---");
            OrderItem orderItem = orderItemManager.add(new OrderItem(ring, 1000));
            System.out.println("--- ALL ORDER ITEMS ---");
            orderItemManager.findAll().stream().forEach(System.out::println);
            System.out.println("--- ORDER ITEM BY ID ---");
            System.out.println(orderItemManager.findById(1));
            System.out.println("--- UPDATE ORDER ITEM ---");
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setSubtotal(1111.11);
            orderItemManager.update(1, orderItemDTO);
            System.out.println("--- DELETE ORDER ITEM ---");
            orderItemManager.delete(2);
            System.out.println("--- ALL ORDER ITEMS UPDATED ---");
            orderItemManager.findAll().stream().forEach(System.out::println);


            OrderManager orderManager = new OrderManager();
            System.out.println("\n\n***************** ORDER *******************");
            System.out.println("--- ADD ORDER ---");
            List<OrderItem> items = new ArrayList<>();
            items.add(orderItem);
            Order order = orderManager.add(new Order(new Customer(1), items));
            System.out.println("--- ALL ORDERS ---");
            orderManager.findAll().stream().forEach(System.out::println);
            System.out.println("--- ORDER BY ID ---");
            System.out.println(orderManager.findById(1));
            System.out.println("--- UPDATE ORDER ---");
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setCustomerId(2);
            orderManager.update(1, orderDTO);
            System.out.println("--- DELETE ORDER ---");
            orderManager.delete(2);
            System.out.println("--- ALL ORDERS UPDATED ---");
            orderManager.findAll().stream().forEach(System.out::println);


            PaymentManager paymentManager = new PaymentManager();
            System.out.println("\n\n***************** PAYMENT *******************");
            System.out.println("--- ADD PAYMENT ---");
            paymentManager.add(new Payment(order, 6677.88, PaymentMethod.CASH));
            System.out.println("--- ALL PAYMENTS ---");
            paymentManager.findAll().stream().forEach(System.out::println);
            System.out.println("--- PAYMENT BY ID ---");
            System.out.println(paymentManager.findById(1));
            System.out.println("--- UPDATE PAYMENT ---");
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setAmount(1111.11);
            paymentManager.update(1, paymentDTO);
            System.out.println("--- DELETE PAYMENT ---");
            paymentManager.delete(2);
            System.out.println("--- ALLPAYMENTS UPDATED ---");
            paymentManager.findAll().stream().forEach(System.out::println);
        } catch (ManagerValidationException e) {
            System.out.println(e.getMessage());
        }
    }
}
