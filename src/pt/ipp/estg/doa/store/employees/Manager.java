package pt.ipp.estg.doa.store.employees;

import pt.ipp.estg.doa.store.dto.EmployeeDTO;

import java.time.LocalDate;

public class Manager extends Employee{

    private String department;
    private double bonus;

    public Manager(int employeeId, String name, String nif, LocalDate hireDate, double salary, String department, double bonus) {
        super(employeeId, name, nif, hireDate, salary, EmployeeType.MANAGER);
        this.department = department;
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return super.toString() + " - Manager{" +
                "department='" + department + '\'' +
                ", bonus=" + bonus +
                '}';
    }

    public void update(EmployeeDTO dto) {
        if (dto.getDepartment() != null) {
            this.setDepartment(dto.getDepartment());
        }
        if (dto.getBonus() != null) {
            this.setBonus(dto.getBonus());
        }
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
