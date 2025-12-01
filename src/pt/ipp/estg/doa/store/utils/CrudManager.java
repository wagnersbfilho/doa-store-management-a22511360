package pt.ipp.estg.doa.store.utils;

import pt.ipp.estg.doa.store.dto.Dto;

import java.util.List;

public interface CrudManager<T extends Entity> {

    List<T> findAll();

    T findById (int id);

    T add(T entity);

    T update (int id, Dto dto);

    void delete (int id);

    //boolean validate (T entity);

}
