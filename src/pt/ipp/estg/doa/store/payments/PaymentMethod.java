package pt.ipp.estg.doa.store.payments;

import pt.ipp.estg.doa.store.orders.OrderStatus;

public enum PaymentMethod {
    CREDIT_CARD(1),
    BANK_TRANSFER(2),
    CASH(3);

    private final int code;

    PaymentMethod(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PaymentMethod getPaymentMethod(int code) {
        for (PaymentMethod method : PaymentMethod.values()) {
            if (method.getCode() == code) {
                return method;
            }
        }
        throw new IllegalArgumentException("Invalid method code: " + code);
    }
}
