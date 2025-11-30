package pt.ipp.estg.doa.store.orders;

import pt.ipp.estg.doa.store.utils.AbstractManager;
import pt.ipp.estg.doa.store.utils.CSVUtilOrderItem;

public class OrderItemManager extends AbstractManager<OrderItem> {

    public OrderItemManager() {
        super(new CSVUtilOrderItem());
    }
}
