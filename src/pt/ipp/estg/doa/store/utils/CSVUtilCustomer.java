package pt.ipp.estg.doa.store.utils;

import pt.ipp.estg.doa.store.customers.Customer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CSVUtilCustomer implements Persistable<Customer> {

    private static final String PATH = "src/pt/ipp/estg/doa/store/utils/csv/";
    private static final String CSV_CUSTOMER_FILE_NAME = PATH + "customer.csv";

    /**
     * Carregar dados de Customer a partir do CSV.
     *
     * @return
     */
    public List<Customer> loadData() {
        List<Customer> emplyeeList = new ArrayList<>();
        try {
            emplyeeList = Files.lines(Path.of(CSV_CUSTOMER_FILE_NAME))
                    .skip(1) // skip header
                    .map(line -> line.split(","))
                    .map(column -> {
                        return new Customer(Integer.parseInt(column[0]),
                                column[1],
                                column[2],
                                column[3],
                                column[4],
                                column[5]);
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
     * @param customers
     */
    public void updateData(List<Customer> customers) {

        Path original = Path.of(CSV_CUSTOMER_FILE_NAME);
        Path temp = Path.of(CSV_CUSTOMER_FILE_NAME + ".tmp");

        try (BufferedWriter writer = Files.newBufferedWriter(temp)) {
            // header
            writer.write("id,name,nif,email,phone,address");
            writer.newLine();

            for (Customer customer: customers) {
                writer.write(String.format(Locale.US, "%d,%s,%s,%s,%s,%s",
                    customer.getId(),
                        customer.getName(),
                        customer.getNif(),
                        customer.getEmail(),
                        customer.getPhone(),
                        customer.getAddress()
                ));
                writer.newLine();
            }

            Files.deleteIfExists(original);
            Files.move(temp, original);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
