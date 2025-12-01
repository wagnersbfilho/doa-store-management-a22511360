package pt.ipp.estg.doa.store.orders;

import pt.ipp.estg.doa.store.utils.AbstractManager;
import pt.ipp.estg.doa.store.utils.CSVUtilOrderItem;

public class OrderItemManager extends AbstractManager<OrderItem> {

    public OrderItemManager() {
        super(new CSVUtilOrderItem());
    }

    @Override
    public boolean validate(OrderItem orderItem) {
        if (orderItem.getJewelry() == null) {
            System.out.println("Jewelry is required");
            return false;
        }
        if (orderItem.getQuantity() <= 0) {
            System.out.println("Quantity is required");
            return false;
        }
        return true;
    }
}
