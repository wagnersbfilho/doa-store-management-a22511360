package pt.ipp.estg.doa.store.orders;

import pt.ipp.estg.doa.store.customers.Customer;
import pt.ipp.estg.doa.store.jewelry.Jewelry;

import java.util.Date;
import java.util.List;

public class Order {

    private int id;
    private Customer customer;
    private Date orderDate;
    private List<OrderItem> jewelryItems;
    private double totalAmount;
    private OrderStatus status;

    public Order(int id, Customer customer, Date orderDate, List<OrderItem> jewelryItems, double totalAmount, OrderStatus status) {
        this.id = id;
        this.customer = customer;
        this.orderDate = orderDate;
        this.jewelryItems = jewelryItems;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", orderDate=" + orderDate +
                ", jewelryItems=" + jewelryItems +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
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
