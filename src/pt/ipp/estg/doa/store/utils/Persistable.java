package pt.ipp.estg.doa.store.utils;

import java.util.List;

public interface Persistable<T extends Entity> {

    List<T> loadData();
    void updateData(List<T> entityList);
}
