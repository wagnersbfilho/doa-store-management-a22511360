package pt.ipp.estg.doa.store.jewelry;

import pt.ipp.estg.doa.store.dto.Dto;
import pt.ipp.estg.doa.store.dto.JewelryDTO;
import pt.ipp.estg.doa.store.utils.Entity;

public class Jewelry extends Entity {

    private int id;
    private String name;
    private JewelryType type;
    private String material;
    private double weight;
    private double price;
    private int stock;
    private Category category;

    public Jewelry(int id, String name, JewelryType type, String material, double weight, double price, int stock, Category category) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.material = material;
        this.weight = weight;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Jewelry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", material='" + material + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", stock=" + stock +
                ", category=" + category +
                '}';
    }

    public void update(Dto dto) {
        JewelryDTO jewelryDTO = (JewelryDTO) dto;
        if (validateType(jewelryDTO)) {
            if (jewelryDTO.getName() != null) this.setName(jewelryDTO.getName());
            if (jewelryDTO.getType() != null) this.setType(jewelryDTO.getType());
            if(jewelryDTO.getMaterial() != null) this.setMaterial(jewelryDTO.getMaterial());
            if(jewelryDTO.getWeight() != null) this.setWeight(jewelryDTO.getWeight());
            if(jewelryDTO.getPrice() != null) this.setPrice(jewelryDTO.getPrice());
            if(jewelryDTO.getStock() != null) this.setStock(jewelryDTO.getStock());
            if(jewelryDTO.getCategory() != null) this.setCategory(jewelryDTO.getCategory());
        }
        if (this instanceof Earring) {
            ((Earring) this).setClaspType(jewelryDTO.getClaspType());
        }
        if (this instanceof Necklace) {
            ((Necklace) this).setLength(jewelryDTO.getLength());
        }
        if (this instanceof Ring) {
            ((Ring) this).setSize(jewelryDTO.getSize());
        }
    }

    private boolean validateType(JewelryDTO dto) {

        boolean earringFieldsUsed = dto.getClaspType() != null;
        boolean necklaceFieldsUsed = dto.getLength() != null;
        boolean ringFieldsUsed = dto.getSize() != null;

        if (earringFieldsUsed && !(this instanceof Earring)) {
            System.out.println("Este jewelry nao é Earring, nao é possivel atualizar.");
            return false;
        }

        if (necklaceFieldsUsed && !(this instanceof Necklace)) {
            System.out.println("Este jewelry nao é Necklace, nao é possivel atualizar.");
            return false;
        }

        if (ringFieldsUsed && !(this instanceof Ring)) {
            System.out.println("Este jewelry nao é Ring, nao é possivel atualizar.");
            return false;
        }

        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JewelryType getType() {
        return type;
    }

    public void setType(JewelryType type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
