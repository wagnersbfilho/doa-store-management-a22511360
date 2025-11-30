package pt.ipp.estg.doa.store.jewelry;

public class Necklace extends Jewelry {

    private double length;

    public Necklace(int id, String name, JewelryType type, String material,
                    double weight, double price, int stock, Category category, double length) {
        super(id, name, type, material, weight, price, stock, category);
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
