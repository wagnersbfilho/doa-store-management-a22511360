package pt.ipp.estg.doa.store.utils;

import pt.ipp.estg.doa.store.jewelry.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CSVUtilJewelry implements Persistable<Jewelry> {

    private static final String PATH = "src/pt/ipp/estg/doa/store/utils/csv/";
    private static final String CSV_EMPLOYEE_FILE_NAME = PATH + "jewelry.csv";

    /**
     * Carregar dados de Employess a partir do CSV.
     *
     * @return
     */
    public List<Jewelry> loadData() {
        List<Jewelry> jewelries = new ArrayList<>();
        try {
            jewelries = Files.lines(Path.of(CSV_EMPLOYEE_FILE_NAME))
                    .skip(1) // skip header
                    .map(line -> line.split(","))
                    .map(column -> {
                        JewelryType jewelrieType = JewelryType.valueOf(column[1]);
                        return switch (jewelrieType) {
                            case EARRING -> new Earring(
                                    Integer.parseInt(column[0]),
                                    column[2],
                                    JewelryType.EARRING,
                                    column[3],
                                    Double.parseDouble(column[4]),
                                    Double.parseDouble(column[5]),
                                    Integer.parseInt(column[6]),
                                    Category.valueOf(column[7]),
                                    column[8]
                            );
                            case NECKLACE -> new Necklace(
                                    Integer.parseInt(column[0]),
                                    column[2],
                                    JewelryType.NECKLACE,
                                    column[3],
                                    Double.parseDouble(column[4]),
                                    Double.parseDouble(column[5]),
                                    Integer.parseInt(column[6]),
                                    Category.valueOf(column[7]),
                                    Double.parseDouble(column[8])
                            );
                            case RING -> new Ring(
                                    Integer.parseInt(column[0]),
                                    column[2],
                                    JewelryType.RING,
                                    column[3],
                                    Double.parseDouble(column[4]),
                                    Double.parseDouble(column[5]),
                                    Integer.parseInt(column[6]),
                                    Category.valueOf(column[7]),
                                    Integer.parseInt(column[8])
                            );
                        };
                    })
                    .collect(Collectors.toList());

        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return jewelries;
    }

    /**
     * Sobrescrever arquivo CSV com nova colecao atualizada recebido como parametro.
     *
     * @param jewelries
     */
    public void updateData(List<Jewelry> jewelries) {

        Path original = Path.of(CSV_EMPLOYEE_FILE_NAME);
        Path temp = Path.of(CSV_EMPLOYEE_FILE_NAME + ".tmp");

        try (BufferedWriter writer = Files.newBufferedWriter(temp)) {
            // header
            writer.write("id,jewelryType,name,material,weight,price,stock,category,additionalField");
            writer.newLine();

            for (Jewelry jewelry: jewelries) {
                if (jewelry instanceof Earring) {
                    writer.write(String.format(Locale.US, "%d,%s,%s,%s,%.2f,%.2f,%d,%s,%s",
                            jewelry.getId(),
                            JewelryType.EARRING.name(),
                            jewelry.getName(),
                            jewelry.getMaterial(),
                            jewelry.getWeight(),
                            jewelry.getPrice(),
                            jewelry.getStock(),
                            jewelry.getCategory().name(),
                            ((Earring) jewelry).getClaspType()
                    ));

                } else  if (jewelry instanceof Necklace) {
                    writer.write(String.format(Locale.US, "%d,%s,%s,%s,%.2f,%.2f,%d,%s,%s",
                            jewelry.getId(),
                            JewelryType.NECKLACE.name(),
                            jewelry.getName(),
                            jewelry.getMaterial(),
                            jewelry.getWeight(),
                            jewelry.getPrice(),
                            jewelry.getStock(),
                            jewelry.getCategory().name(),
                            ((Necklace) jewelry).getLength()
                    ));
                } else  if (jewelry instanceof Ring) {
                    writer.write(String.format(Locale.US, "%d,%s,%s,%s,%.2f,%.2f,%d,%s,%s",
                            jewelry.getId(),
                            JewelryType.RING.name(),
                            jewelry.getName(),
                            jewelry.getMaterial(),
                            jewelry.getWeight(),
                            jewelry.getPrice(),
                            jewelry.getStock(),
                            jewelry.getCategory().name(),
                            ((Ring) jewelry).getSize()
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
