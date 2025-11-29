package pt.ipp.estg.doa.store.employees;

import java.util.Date;

public class Manager extends Employee{

    private String department;
    private double bonus;

    public Manager(int employeeId, String name, String nif, Date hireDate, double salary) {
        super(employeeId, name, nif, hireDate, salary);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
