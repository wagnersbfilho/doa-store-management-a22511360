package pt.ipp.estg.doa.store.utils;

import pt.ipp.estg.doa.store.jewelry.Jewelry;
import pt.ipp.estg.doa.store.orders.Order;
import pt.ipp.estg.doa.store.orders.OrderItem;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CSVUtilOrderItem implements Persistable<OrderItem> {

    private static final String PATH = "src/pt/ipp/estg/doa/store/utils/csv/";
    private static final String CSV_ORDER_ITEM_FILE_NAME = PATH + "order_item.csv";

    /**
     * Carregar dados de OrderItem a partir do CSV.
     *
     * @return
     */
    public List<OrderItem> loadData() {
        List<OrderItem> orderList = new ArrayList<>();
        try {
            orderList = Files.lines(Path.of(CSV_ORDER_ITEM_FILE_NAME))
                    .skip(1) // skip header
                    .map(line -> line.split(","))
                    .map(column ->
                        new OrderItem(
                            Integer.parseInt(column[0]),
                            new Order(Integer.parseInt(column[1])),
                            new Jewelry(Integer.parseInt(column[2])),
                            Integer.parseInt(column[3]),
                            Double.parseDouble(column[4])))
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
    public void updateData(List<OrderItem> orders) {

        Path original = Path.of(CSV_ORDER_ITEM_FILE_NAME);
        Path temp = Path.of(CSV_ORDER_ITEM_FILE_NAME + ".tmp");

        try (BufferedWriter writer = Files.newBufferedWriter(temp)) {
            // header
            writer.write("id,orderId,jewelryId,quantity,subtotal");
            writer.newLine();

            for (OrderItem orderItem: orders) {
                writer.write(String.format(Locale.US, "%d,%d,%d,%d,%.2f",
                    orderItem.getId(),
                        orderItem.getOrder().getId(),
                        orderItem.getJewelry().getId(),
                        orderItem.getQuantity(),
                        orderItem.getSubtotal()
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
