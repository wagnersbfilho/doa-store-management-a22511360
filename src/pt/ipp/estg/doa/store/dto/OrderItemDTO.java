package pt.ipp.estg.doa.store.dto;

public class OrderItemDTO extends Dto{

    private Integer id;
    private Integer orderId;
    private Integer jewelryId;
    private Integer quantity;
    private Double subtotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getJewelryId() {
        return jewelryId;
    }

    public void setJewelryId(Integer jewelryId) {
        this.jewelryId = jewelryId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
