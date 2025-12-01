package pt.ipp.estg.doa.store.orders;

import pt.ipp.estg.doa.store.excpetion.ManagerValidationException;
import pt.ipp.estg.doa.store.utils.AbstractManager;
import pt.ipp.estg.doa.store.utils.CSVUtilOrderItem;

public class OrderItemManager extends AbstractManager<OrderItem> {

    public OrderItemManager() {
        super(new CSVUtilOrderItem());
    }

    @Override
    public void validate(OrderItem orderItem) throws ManagerValidationException {
        if (orderItem.getJewelry() == null) {
            throw new ManagerValidationException("Jewelry is required");
        }
        if (orderItem.getQuantity() <= 0) {
            throw new ManagerValidationException("Quantity is required");
        }
    }
}
