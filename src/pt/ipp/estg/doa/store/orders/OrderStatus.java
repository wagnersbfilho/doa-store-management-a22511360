package pt.ipp.estg.doa.store.orders;

public enum OrderStatus {
    PENDING(1),
    ACCEPTED(2),
    DELIVERED(3),
    CANCELED(4);

    private final int code;

    OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderStatus getOrderStatus(int code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + code);
    }
}
