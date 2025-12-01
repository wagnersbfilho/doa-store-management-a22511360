package pt.ipp.estg.doa.store.utils;

import pt.ipp.estg.doa.store.dto.Dto;
import pt.ipp.estg.doa.store.excpetion.ManagerValidationException;

import java.util.List;

public interface CrudManager<T extends Entity> {

    List<T> findAll();

    T findById (int id) throws ManagerValidationException ;

    T add(T entity) throws ManagerValidationException;

    T update (int id, Dto dto) throws ManagerValidationException;

    void delete (int id) throws ManagerValidationException;
}
