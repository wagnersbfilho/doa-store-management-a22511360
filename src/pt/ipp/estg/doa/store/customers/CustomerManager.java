package pt.ipp.estg.doa.store.customers;

import pt.ipp.estg.doa.store.utils.AbstractManager;
import pt.ipp.estg.doa.store.utils.CSVUtilCustomer;

public class CustomerManager extends AbstractManager<Customer> {

    public CustomerManager() {
        super(new CSVUtilCustomer());
    }
}
