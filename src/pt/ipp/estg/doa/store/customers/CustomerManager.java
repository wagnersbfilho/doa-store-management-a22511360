package pt.ipp.estg.doa.store.customers;

import pt.ipp.estg.doa.store.excpetion.ManagerValidationException;
import pt.ipp.estg.doa.store.utils.AbstractManager;
import pt.ipp.estg.doa.store.utils.CSVUtilCustomer;
import pt.ipp.estg.doa.store.utils.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager extends AbstractManager<Customer> {

    public CustomerManager() {
        super(new CSVUtilCustomer());
    }

    public List<Customer> findByName(String name) throws ManagerValidationException {
        List<Customer> customers = findAll();
        List<Customer> result = customers.stream()
                .filter(customer -> customer.getName().toUpperCase().contains(name.toUpperCase()))
                .toList();
        if (result.isEmpty()) throw new ManagerValidationException("Any customer found for this name: " + name);
        return result;
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
    public void validate(Customer customer) throws ManagerValidationException {
        if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new ManagerValidationException("Name is required");
        }
        if (customer.getNif() == null || customer.getNif().isEmpty()) {
            throw new ManagerValidationException("NIF is required");
        }
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            throw new ManagerValidationException("Email is required");
        }
        if (customer.getPhone() == null || customer.getPhone().isEmpty()) {
            throw new ManagerValidationException("Phone is required");
        }
        if (customer.getAddress() == null || customer.getAddress().isEmpty()) {
            throw new ManagerValidationException("Address is required");
        }
        if (!customer.getNif().matches(ValidationUtil.FORMAT_9_DIGITS)) {
            throw new ManagerValidationException("NIF is not valid");
        }
        if (!customer.getPhone().matches(ValidationUtil.FORMAT_9_DIGITS)) {
            throw new ManagerValidationException("Phone is not valid");
        }
        if (!customer.getEmail().matches(ValidationUtil.FORMAT_EMAIL)) {
            throw new ManagerValidationException("Email is not valid");
        }
    }
}
