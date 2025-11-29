package pt.ipp.estg.doa.store.employees;

import java.util.Date;

public class Employee {

    private int id;
    private String name;
    private String nif;
    private Date hireDate;
    private double salary;

    public Employee(int id, String name, String nif, Date hireDate, double salary) {
        this.id = id;
        this.name = name;
        this.nif = nif;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nif='" + nif + '\'' +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
