package pt.ipp.estg.doa.store.utils;

import pt.ipp.estg.doa.store.orders.Order;
import pt.ipp.estg.doa.store.payments.Payment;
import pt.ipp.estg.doa.store.payments.PaymentMethod;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CSVUtilPayment implements Persistable<Payment> {

    private static final String PATH = "src/pt/ipp/estg/doa/store/utils/csv/";
    private static final String CSV_PAYMENT_FILE_NAME = PATH + "payment.csv";

    /**
     * Carregar dados de Payment a partir do CSV.
     *
     * @return
     */
    public List<Payment> loadData() {
        List<Payment> orderList = new ArrayList<>();
        try {
            orderList = Files.lines(Path.of(CSV_PAYMENT_FILE_NAME))
                    .skip(1) // skip header
                    .map(line -> line.split(","))
                    .map(column ->
                        new Payment(Integer.parseInt(column[0]),
                            new Order(Integer.parseInt(column[1])),
                            Double.parseDouble(column[2]),
                            LocalDate.parse(column[3], ValidationUtil.FORMAT_DATE),
                            PaymentMethod.valueOf(column[4])))
                    .collect(Collectors.toList());

        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return orderList;
    }

    /**
     * Sobrescrever arquivo CSV com nova colecao atualizada recebido como parametro.
     *
     * @param payments
     */
    public void updateData(List<Payment> payments) {

        Path original = Path.of(CSV_PAYMENT_FILE_NAME);
        Path temp = Path.of(CSV_PAYMENT_FILE_NAME + ".tmp");

        try (BufferedWriter writer = Files.newBufferedWriter(temp)) {
            // header
            writer.write("id,orderId,amount,paymentDate,paymentMethod");
            writer.newLine();

            for (Payment payment: payments) {
                writer.write(String.format(Locale.US, "%d,%d,%.2f,%s,%s",
                    payment.getId(),
                        payment.getOrder().getId(),
                        payment.getAmount(),
                        payment.getPaymentDate().format(ValidationUtil.FORMAT_DATE),
                        payment.getPaymentMethod().name()
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
