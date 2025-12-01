package pt.ipp.estg.doa.store.utils;

import pt.ipp.estg.doa.store.customers.Customer;
import pt.ipp.estg.doa.store.orders.Order;
import pt.ipp.estg.doa.store.orders.OrderStatus;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CSVUtilOrder implements Persistable<Order> {

    private static final String PATH = "src/pt/ipp/estg/doa/store/utils/csv/";
    private static final String CSV_ORDER_FILE_NAME = PATH + "order.csv";

    /**
     * Carregar dados de Order a partir do CSV.
     *
     * @return
     */
    public List<Order> loadData() {
        List<Order> orderList = new ArrayList<>();
        try {
            orderList = Files.lines(Path.of(CSV_ORDER_FILE_NAME))
                    .skip(1) // skip header
                    .map(line -> line.split(","))
                    .map(column -> {
                        return new Order(Integer.parseInt(column[0]),
                                new Customer(Integer.parseInt(column[1])),
                                LocalDate.parse(column[2], ValidationUtil.FORMAT_DATE),
                                Double.parseDouble(column[3]),
                                OrderStatus.valueOf(column[4]));
                    })
                    .collect(Collectors.toList());

        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return orderList;
    }

    /**
     * Sobrescrever arquivo CSV com nova colecao atualizada recebido como parametro.
     *
     * @param orders
     */
    public void updateData(List<Order> orders) {

        Path original = Path.of(CSV_ORDER_FILE_NAME);
        Path temp = Path.of(CSV_ORDER_FILE_NAME + ".tmp");

        try (BufferedWriter writer = Files.newBufferedWriter(temp)) {
            // header
            writer.write("id,customerId,orderDate,totalAmount,status");
            writer.newLine();

            for (Order order: orders) {
                writer.write(String.format(Locale.US, "%d,%d,%s,%.2f,%s",
                    order.getId(),
                        order.getCustomer().getId(),
                        order.getOrderDate().format(ValidationUtil.FORMAT_DATE),
                        order.getTotalAmount(),
                        order.getStatus().name()
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
