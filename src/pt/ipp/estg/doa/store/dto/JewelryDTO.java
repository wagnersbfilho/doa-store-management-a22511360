package pt.ipp.estg.doa.store.dto;

import pt.ipp.estg.doa.store.jewelry.Category;
import pt.ipp.estg.doa.store.jewelry.JewelryType;

public class JewelryDTO extends Dto {

    private Integer id;
    private String name;
    private JewelryType type;
    private String material;
    private Double weight;
    private Double price;
    private Integer stock;
    private Category category;

    // Campos específicos
    private String claspType;
    private Double length;
    private Integer size;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getClaspType() {
        return claspType;
    }

    public void setClaspType(String claspType) {
        this.claspType = claspType;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
