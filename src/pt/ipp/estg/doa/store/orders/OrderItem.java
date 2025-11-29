package pt.ipp.estg.doa.store.orders;

import pt.ipp.estg.doa.store.jewelry.Jewelry;

public class OrderItem {

    private Jewelry jewelry;
    private int quantity;
    private double subtotal;

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
