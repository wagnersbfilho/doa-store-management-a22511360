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
}
