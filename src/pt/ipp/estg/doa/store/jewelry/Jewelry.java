package pt.ipp.estg.doa.store.jewelry;

public class Jewelry {

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
