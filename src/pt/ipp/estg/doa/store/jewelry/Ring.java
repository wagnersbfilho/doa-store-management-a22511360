package pt.ipp.estg.doa.store.jewelry;

public class Ring extends Jewelry {

    private int size;

    public Ring(int id, String name, JewelryType type, String material, double weight, double price, int stock, Category category) {
        super(id, name, type, material, weight, price, stock, category);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
