package pt.ipp.estg.doa.store.customers;

import pt.ipp.estg.doa.store.utils.AbstractManager;
import pt.ipp.estg.doa.store.utils.CSVUtilCustomer;
import pt.ipp.estg.doa.store.utils.ValidationUtil;

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

    @Override
    public boolean validate(Customer customer) {
        if (customer.getName() == null || customer.getName().isEmpty()) {
            System.out.println("Name is required");
            return false;
        }
        if (customer.getNif() == null || customer.getNif().isEmpty()) {
            System.out.println("NIF is required");
            return false;
        }
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            System.out.println("Email is required");
            return false;
        }
        if (customer.getPhone() == null || customer.getPhone().isEmpty()) {
            System.out.println("Phone is required");
            return false;
        }
        if (customer.getAddress() == null || customer.getAddress().isEmpty()) {
            System.out.println("Address is required");
            return false;
        }
        if (!customer.getNif().matches(ValidationUtil.FORMAT_9_DIGITS)) {
            System.out.println("NIF is not valid");
            return false;
        }
        if (!customer.getPhone().matches(ValidationUtil.FORMAT_9_DIGITS)) {
            System.out.println("Phone is not valid");
            return false;
        }
        if (!customer.getEmail().matches(ValidationUtil.FORMAT_EMAIL)) {
            System.out.println("Email is not valid");
            return false;
        }
        return true;
    }
}
