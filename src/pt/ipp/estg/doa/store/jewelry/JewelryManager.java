package pt.ipp.estg.doa.store.jewelry;

import pt.ipp.estg.doa.store.utils.AbstractManager;
import pt.ipp.estg.doa.store.utils.CSVUtilJewelry;

import java.util.List;

public class JewelryManager extends AbstractManager<Jewelry> {

    private static final int STOCK_THRESHOLD = 4;

    public JewelryManager() {
        super(new CSVUtilJewelry());
    }

    public List<Jewelry> findByName(String name) {
        List<Jewelry> jewelries = findAll();
        return jewelries.stream()
                .filter(jewelry -> jewelry.getName().toUpperCase().contains(name.toUpperCase()))
                .toList();
    }

    public List<Jewelry> findByMaterial(String material) {
        List<Jewelry> jewelries = findAll();
        return jewelries.stream()
                .filter(jewelry -> jewelry.getMaterial().toUpperCase().contains(material.toUpperCase()))
                .toList();
    }

    public List<Jewelry> findByType(JewelryType type) {
        List<Jewelry> jewelries = findAll();
        return jewelries.stream()
                .filter(jewelry -> jewelry.getType().equals(type))
                .toList();
    }

    public List<Jewelry> findByCategory(Category category) {
        List<Jewelry> jewelries = findAll();
        return jewelries.stream()
                .filter(jewelry -> jewelry.getCategory().equals(category))
                .toList();
    }

    public boolean isInStock(String name) {
        return !findByName(name).isEmpty();
    }

    public boolean isLowStock(String name) {
        return findByName(name).size() < STOCK_THRESHOLD;
    }
}
