package pt.ipp.estg.doa.store.customers;

import pt.ipp.estg.doa.store.utils.AbstractManager;
import pt.ipp.estg.doa.store.utils.CSVUtilCustomer;

import java.util.List;

public class CustomerManager extends AbstractManager<Customer> {

    public CustomerManager() {
        super(new CSVUtilCustomer());
    }

    public List<Customer> findByName(String name) {
        List<Customer> customers = findAll();
        return customers.stream()
                .filter(customer -> customer.getName().toUpperCase().contains(name.toUpperCase()))
                .toList();
    }

    public List<Customer> findByNif(String nif) {
        List<Customer> customers = findAll();
        return customers.stream()
                .filter(customer -> customer.getNif().contains(nif))
                .toList();
    }

    public List<Customer> findByEmail(String email) {
        List<Customer> customers = findAll();
        return customers.stream()
                .filter(customer -> customer.getEmail().toUpperCase().contains(email.toUpperCase()))
                .toList();
    }

}
