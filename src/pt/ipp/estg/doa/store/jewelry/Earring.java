package pt.ipp.estg.doa.store.jewelry;

public class Earring extends Jewelry{

    private String claspType;


    public Earring(int id, String name, JewelryType type, String material, double weight, double price, int stock, Category category) {
        super(id, name, type, material, weight, price, stock, category);
    }

    public String getClaspType() {
        return claspType;
    }

    public void setClaspType(String claspType) {
        this.claspType = claspType;
    }
}
