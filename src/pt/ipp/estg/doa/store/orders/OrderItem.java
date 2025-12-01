package pt.ipp.estg.doa.store.orders;

import pt.ipp.estg.doa.store.dto.Dto;
import pt.ipp.estg.doa.store.dto.OrderItemDTO;
import pt.ipp.estg.doa.store.jewelry.Jewelry;
import pt.ipp.estg.doa.store.utils.Entity;

public class OrderItem extends Entity {

    private int id;
    private Jewelry jewelry;
    private int quantity;
    private double subtotal;

    public OrderItem(Jewelry jewelry, int quantity) {
        this.jewelry = jewelry;
        this.quantity = quantity;
        this.subtotal = jewelry.getPrice() * quantity;
    }

    public OrderItem(int id, Jewelry jewelry, int quantity, double subtotal) {
        this.id = id;
        this.jewelry = jewelry;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", jewelry=" + (jewelry !=  null ? jewelry.getId() : null) +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }

    @Override
    public <T extends Dto> void update(T dto) {
        OrderItemDTO  orderItemDTO = (OrderItemDTO) dto;
        if(orderItemDTO.getJewelryId() != null) this.setJewelry(new Jewelry(orderItemDTO.getJewelryId()));
        if(orderItemDTO.getQuantity() != null) this.setQuantity(orderItemDTO.getQuantity());
        if(orderItemDTO.getSubtotal() != null) this.setSubtotal(orderItemDTO.getSubtotal());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jewelry getJewelry() {
        return jewelry;
    }

    public void setJewelry(Jewelry jewelry) {
        this.jewelry = jewelry;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
