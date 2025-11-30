package pt.ipp.estg.doa.store.employees;

import pt.ipp.estg.doa.store.dto.Dto;
import pt.ipp.estg.doa.store.dto.EmployeeDTO;
import pt.ipp.estg.doa.store.utils.Entity;

import java.time.LocalDate;

public class Employee extends Entity {

    private String name;
    private String nif;
    private LocalDate hireDate;
    private double salary;
    private EmployeeType type;

    public Employee(int id, String name, String nif, LocalDate hireDate, double salary, EmployeeType type) {
        setId(id);
        this.name = name;
        this.nif = nif;
        this.hireDate = hireDate;
        this.salary = salary;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", nif='" + nif + '\'' +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                '}';
    }

    public void update(Dto dto) {
        EmployeeDTO employeeDTO = (EmployeeDTO) dto;
        if (validateType(employeeDTO)) {
            if (employeeDTO.name != null) this.setName(employeeDTO.name);
            if (employeeDTO.nif != null) this.setNif(employeeDTO.nif);
            if (employeeDTO.hireDate != null) this.setHireDate(employeeDTO.hireDate);
            if (employeeDTO.salary != null) this.setSalary(employeeDTO.salary);
        }
        if (this instanceof SalesPerson) {
            ((SalesPerson) this).update(employeeDTO);
        }
        if (this instanceof Manager) {
            ((Manager) this).update(employeeDTO);
        }
    }

    private boolean validateType(EmployeeDTO dto) {

        boolean salesFieldsUsed = dto.commissionRate != null || dto.totalSales != null;
        boolean managerFieldsUsed = dto.department != null || dto.bonus != null;

        if (salesFieldsUsed && !(this instanceof SalesPerson)) {
            System.out.println("Este employee nao é SalesPerson, nao é possivel atualizar dados de vendas.");
            return false;
        }

        if (managerFieldsUsed && !(this instanceof Manager)) {
            System.out.println("Este employee nao é Manager, nao é possivel atualizar dados de manager.");
            return false;
        }

        return true;
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

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }
}
