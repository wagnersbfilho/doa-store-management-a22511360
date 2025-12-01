package pt.ipp.estg.doa.store.utils;

import pt.ipp.estg.doa.store.employees.Employee;
import pt.ipp.estg.doa.store.employees.EmployeeType;
import pt.ipp.estg.doa.store.employees.Manager;
import pt.ipp.estg.doa.store.employees.SalesPerson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CSVUtilEmployee implements Persistable<Employee> {

    private static final String PATH = "src/pt/ipp/estg/doa/store/utils/csv/";
    private static final String CSV_EMPLOYEE_FILE_NAME = PATH + "employee.csv";

    /**
     * Carregar dados de Employess a partir do CSV.
     *
     * @return
     */
    public List<Employee> loadData() {
        List<Employee> emplyeeList = new ArrayList<>();
        try {
            emplyeeList = Files.lines(Path.of(CSV_EMPLOYEE_FILE_NAME))
                    .skip(1) // skip header
                    .map(line -> line.split(","))
                    .map(column -> {
                        EmployeeType employeeType = EmployeeType.valueOf(column[1]);
                        return switch (employeeType) {
                            case SALESPERSON -> new SalesPerson(
                                    Integer.parseInt(column[0]),
                                    column[2],
                                    column[3],
                                    LocalDate.parse(column[4], ValidationUtil.FORMAT_DATE),
                                    Double.parseDouble(column[5]),
                                    Double.parseDouble(column[6]),
                                    Double.parseDouble(column[7])
                            );
                            case MANAGER -> new Manager(
                                    Integer.parseInt(column[0]),
                                    column[2],
                                    column[3],
                                    LocalDate.parse(column[4], ValidationUtil.FORMAT_DATE),
                                    Double.parseDouble(column[5]),
                                    column[6],
                                    Double.parseDouble(column[7])
                            );
                        };
                    })
                    .collect(Collectors.toList());

        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return emplyeeList;
    }

    /**
     * Sobrescrever arquivo CSV com nova colecao atualizada recebido como parametro.
     *
     * @param employees
     */
    public void updateData(List<Employee> employees) {

        Path original = Path.of(CSV_EMPLOYEE_FILE_NAME);
        Path temp = Path.of(CSV_EMPLOYEE_FILE_NAME + ".tmp");

        try (BufferedWriter writer = Files.newBufferedWriter(temp)) {
            // header
            writer.write("id,employeeType,name,nif,hireDate,salary,additionalField1,additionalField2");
            writer.newLine();

            for (Employee employee: employees) {
                if (employee instanceof SalesPerson) {
                    writer.write(String.format(Locale.US, "%d,%s,%s,%s,%s,%.2f,%.2f,%.2f",
                            employee.getId(),
                            EmployeeType.SALESPERSON.name(),
                            employee.getName(),
                            employee.getNif(),
                            employee.getHireDate().format(ValidationUtil.FORMAT_DATE),
                            employee.getSalary(),
                            ((SalesPerson) employee).getCommissionRate(),
                            ((SalesPerson) employee).getTotalSales()
                    ));

                } else  if (employee instanceof Manager) {
                    writer.write(String.format(Locale.US, "%d,%s,%s,%s,%s,%.2f,%s,%.2f",
                            employee.getId(),
                            EmployeeType.MANAGER.name(),
                            employee.getName(),
                            employee.getNif(),
                            employee.getHireDate().format(ValidationUtil.FORMAT_DATE),
                            employee.getSalary(),
                            ((Manager) employee).getDepartment(),
                            ((Manager) employee).getBonus()
                    ));
                }
                writer.newLine();
            }

            Files.deleteIfExists(original);
            Files.move(temp, original);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
