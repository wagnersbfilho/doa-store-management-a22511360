package pt.ipp.estg.doa.store.employees;

import java.util.Date;

public class Salesperson extends Employee{

    private double commissionRate;
    private double totalSales;

    public Salesperson(int employeeId, String name, String nif, Date hireDate, double salary) {
        super(employeeId, name, nif, hireDate, salary);
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }
}
