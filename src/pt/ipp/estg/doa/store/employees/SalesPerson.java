package pt.ipp.estg.doa.store.employees;

import pt.ipp.estg.doa.store.dto.EmployeeDTO;

import java.time.LocalDate;

public class SalesPerson extends Employee{

    private double commissionRate;
    private double totalSales;

    public SalesPerson(int employeeId, String name, String nif, LocalDate hireDate, double salary,
                       double commissionRate, double totalSales) {
        super(employeeId, name, nif, hireDate, salary, EmployeeType.SALESPERSON);
        this.commissionRate = commissionRate;
        this.totalSales = totalSales;
    }

    @Override
    public String toString() {
        return super.toString() + " - SalesPerson{" +
                "commissionRate=" + commissionRate +
                ", totalSales=" + totalSales +
                '}';
    }

    public void update(EmployeeDTO dto) {
        if (dto.commissionRate != null) {
            this.setCommissionRate(dto.commissionRate);
        }
        if (dto.totalSales != null) {
            this.setTotalSales(dto.totalSales);
        }
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
