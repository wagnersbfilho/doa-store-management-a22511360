package pt.ipp.estg.doa.store.customers;

import pt.ipp.estg.doa.store.dto.CustomerDTO;
import pt.ipp.estg.doa.store.dto.Dto;
import pt.ipp.estg.doa.store.utils.Entity;

public class Customer extends Entity {

    private String name;
    private String nif;
    private String email;
    private String address;
    private String phone;

    public Customer(int id) {
        setId(id);
    }

    public Customer(String name, String nif, String email, String address, String phone) {
        this.name = name;
        this.nif = nif;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public Customer(int id, String name, String nif, String email, String address, String phone) {
        setId(id);
        this.name = name;
        this.nif = nif;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", nif='" + nif + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public <T extends Dto> void update(T dto) {
        CustomerDTO customerDTO = (CustomerDTO) dto;
        if (customerDTO.getId() != null) this.setId(customerDTO.getId());
        if (customerDTO.getName() != null) this.setName(customerDTO.getName());
        if (customerDTO.getNif() != null) this.setNif(customerDTO.getNif());
        if (customerDTO.getEmail() != null) this.setEmail(customerDTO.getEmail());
        if (customerDTO.getAddress() != null) this.setAddress(customerDTO.getAddress());
        if (customerDTO.getPhone() != null) this.setPhone(customerDTO.getPhone());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
