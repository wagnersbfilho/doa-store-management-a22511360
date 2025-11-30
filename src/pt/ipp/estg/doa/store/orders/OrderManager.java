package pt.ipp.estg.doa.store.orders;

import pt.ipp.estg.doa.store.utils.AbstractManager;
import pt.ipp.estg.doa.store.utils.CSVUtilOrder;

public class OrderManager extends AbstractManager<Order> {

    public OrderManager() {
        super(new CSVUtilOrder());
    }
}
