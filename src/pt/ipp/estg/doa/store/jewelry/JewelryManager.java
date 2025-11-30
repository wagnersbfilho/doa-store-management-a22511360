package pt.ipp.estg.doa.store.jewelry;

import pt.ipp.estg.doa.store.utils.AbstractManager;
import pt.ipp.estg.doa.store.utils.CSVUtilJewelry;

public class JewelryManager extends AbstractManager<Jewelry> {

    public JewelryManager() {
        super(new CSVUtilJewelry());
    }
}
