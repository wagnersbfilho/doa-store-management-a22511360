package pt.ipp.estg.doa.store.payments;

import pt.ipp.estg.doa.store.dto.Dto;
import pt.ipp.estg.doa.store.dto.PaymentDTO;
import pt.ipp.estg.doa.store.orders.Order;
import pt.ipp.estg.doa.store.utils.Entity;

import java.time.LocalDate;

public class Payment extends Entity {

    private int id;
    private Order order;
    private double amount;
    private LocalDate paymentDate;
    private PaymentMethod paymentMethod;

    public Payment(int id, Order order, double amount, LocalDate paymentDate, PaymentMethod paymentMethod) {
        this.id = id;
        this.order = order;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", order=" + (order != null ? order.getId() : null) +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", paymentMethod=" + paymentMethod +
                '}';
    }

    @Override
    public <T extends Dto> void update(T dto) {
        PaymentDTO paymentDTO = (PaymentDTO) dto;
        if (paymentDTO.getOrderId() != null) this.setOrder(new Order(paymentDTO.getOrderId()));
        if (paymentDTO.getPaymentDate() != null) this.setPaymentDate(paymentDTO.getPaymentDate());
        if (paymentDTO.getPaymentMethod() != null) this.setPaymentMethod(paymentDTO.getPaymentMethod());
        if (paymentDTO.getAmount() != null) this.setAmount(paymentDTO.getAmount());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
