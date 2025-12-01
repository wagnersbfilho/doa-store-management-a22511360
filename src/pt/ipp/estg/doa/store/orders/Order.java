package pt.ipp.estg.doa.store.orders;

import pt.ipp.estg.doa.store.customers.Customer;
import pt.ipp.estg.doa.store.dto.Dto;
import pt.ipp.estg.doa.store.dto.OrderDTO;
import pt.ipp.estg.doa.store.jewelry.Jewelry;
import pt.ipp.estg.doa.store.utils.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order extends Entity {

    private Customer customer;
    private LocalDate orderDate;
    private List<OrderItem> jewelryItems;
    private double totalAmount;
    private OrderStatus status;

    public Order(Customer customer, List<OrderItem> jewelryItems) {
        this.customer = customer;
        this.orderDate = LocalDate.now();
        this.jewelryItems = jewelryItems != null ? jewelryItems : new ArrayList<>();
        this.totalAmount = this.jewelryItems.stream().mapToDouble(OrderItem::getSubtotal).sum();
        this.status = OrderStatus.PENDING;
    }

    public Order(int id, Customer customer, LocalDate orderDate, double totalAmount, OrderStatus status) {
        setId(id);
        this.customer = customer;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Order(int id) {
        setId(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + getId() +
                ", customer=" + (customer != null ? customer.getId() : null) +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }

    @Override
    public <T extends Dto> void update(T dto) {
        OrderDTO orderDTO = (OrderDTO) dto;
        if (orderDTO.getCustomerId() != null) this.setCustomer(new Customer(orderDTO.getCustomerId()));
        if (orderDTO.getOrderDate() != null) this.setOrderDate(orderDTO.getOrderDate());
        if (orderDTO.getTotalAmount() != null) this.setTotalAmount(orderDTO.getTotalAmount());
        if (orderDTO.getStatus() != null) this.setStatus(orderDTO.getStatus());
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItem> getJewelryItems() {
        return jewelryItems;
    }

    public void setJewelryItems(List<OrderItem> jewelryItems) {
        this.jewelryItems = jewelryItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
