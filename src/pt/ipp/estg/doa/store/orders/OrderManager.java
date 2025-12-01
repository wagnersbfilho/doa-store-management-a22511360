package pt.ipp.estg.doa.store.orders;

import pt.ipp.estg.doa.store.excpetion.ManagerValidationException;
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
    public void validate(Order order) throws ManagerValidationException {
        if (order.getCustomer() == null  || order.getCustomer().getId() == 0) {
            throw new ManagerValidationException("Customer is required");
        }
        if (order.getOrderDate() == null) {
            throw new ManagerValidationException("Order Date is required");
        }
        if (order.getStatus() == null) {
            throw new ManagerValidationException("Status is required");
        }
        if (order.getJewelryItems() == null || order.getJewelryItems().isEmpty()) {
            throw new ManagerValidationException("Order Items (Jewelry) is required");
        }
    }
}
