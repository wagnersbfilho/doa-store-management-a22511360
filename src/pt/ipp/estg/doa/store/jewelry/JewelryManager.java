package pt.ipp.estg.doa.store.jewelry;

import pt.ipp.estg.doa.store.excpetion.ManagerValidationException;
import pt.ipp.estg.doa.store.utils.AbstractManager;
import pt.ipp.estg.doa.store.utils.CSVUtilJewelry;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<Jewelry> jewelries = findByName(name);
        return jewelries.stream()
                .noneMatch(jewelry -> jewelry.getStock() <= 0);
    }

    public List<Jewelry> findLowStock() {
        List<Jewelry> jewelries = findAll();
        return jewelries.stream()
                .filter(jewelry -> jewelry.getStock() < STOCK_THRESHOLD)
                .collect(Collectors.toList());
    }

    @Override
    public void validate(Jewelry jewelry) throws ManagerValidationException {
        if (jewelry.getName() == null || jewelry.getName().isEmpty()) {
            throw new ManagerValidationException("Name is required");
        }
        if (jewelry.getType() == null) {
            throw new ManagerValidationException("Type is required");
        }
        if (jewelry.getCategory() == null) {
            throw new ManagerValidationException("Category is required");
        }
        if (jewelry.getMaterial() == null || jewelry.getMaterial().isEmpty()) {
            throw new ManagerValidationException("Material is required");
        }
        if (jewelry.getWeight() <= 0) {
            throw new ManagerValidationException("Weight is required");
        }
        if (jewelry.getPrice() <= 0) {
            throw new ManagerValidationException("Price is required");
        }
        if (jewelry.getStock() < 0) {
            throw new ManagerValidationException("Stock is required");
        }
        if (jewelry instanceof Earring) {
            if (((Earring) jewelry).getClaspType() == null) {
                throw new ManagerValidationException("Earring ClaspType is required");
            }
        }
        if (jewelry instanceof Necklace) {
            if (((Necklace) jewelry).getLength() <= 0) {
                throw new ManagerValidationException("Necklace length is required");
            }
        }
        if (jewelry instanceof Ring) {
            int size = ((Ring) jewelry).getSize();
            if (size <= 10 || size >= 30) {
                throw new ManagerValidationException("Ring size must be between 10 and 30 (European standard)");
            }
        }
    }
}
