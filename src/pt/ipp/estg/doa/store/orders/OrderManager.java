package pt.ipp.estg.doa.store.orders;

import pt.ipp.estg.doa.store.utils.AbstractManager;
import pt.ipp.estg.doa.store.utils.CSVUtilOrder;

import java.time.LocalDate;
import java.util.List;

public class OrderManager extends AbstractManager<Order> {

    public OrderManager() {
        super(new CSVUtilOrder());
    }

    public List<Order> findByCustomer(int idCustomer) {
        List<Order> orders = findAll();
        return orders.stream()
                .filter(order -> order.getCustomer().getId() == idCustomer)
                .toList();
    }

    public List<Order> findByStatus(OrderStatus status) {
        List<Order> orders = findAll();
        return orders.stream()
                .filter(order -> order.getStatus().equals(status))
                .toList();
    }

    public List<Order> findByDataRange(LocalDate beginDate, LocalDate endDate) {
        List<Order> orders = findAll();
        return orders.stream()
                .filter(order -> !order.getOrderDate().isBefore(beginDate)
                        && !order.getOrderDate().isAfter(endDate))
                .toList();
    }

    public double calculateTotalRevenue(){
        List<Order> orders = findAll();
        return orders.stream().mapToDouble(Order::getTotalAmount).sum();
    }

    @Override
    public boolean validate(Order order) {
        if (order.getCustomer() == null  || order.getCustomer().getId() == 0) {
            System.out.println("Customer is required");
            return false;
        }
        if (order.getOrderDate() == null) {
            System.out.println("Order Date is required");
            return false;
        }
        if (order.getStatus() == null) {
            System.out.println("Status is required");
            return false;
        }
        if (order.getJewelryItems() == null || order.getJewelryItems().isEmpty()) {
            System.out.println("Order Items (Jewelry) is required");
            return false;
        }
        return true;
    }
}
