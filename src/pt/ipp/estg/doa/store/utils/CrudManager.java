package pt.ipp.estg.doa.store.utils;

import pt.ipp.estg.doa.store.dto.Dto;

import java.util.List;

public interface CrudManager<T extends Entity> {

    List<T> findAll();

    T findById (int id);

    void add(T entity);

    void update (int id, Dto dto);

    void delete (int id);

}
