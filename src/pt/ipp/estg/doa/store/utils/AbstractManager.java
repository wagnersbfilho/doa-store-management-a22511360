package pt.ipp.estg.doa.store.utils;

import pt.ipp.estg.doa.store.dto.Dto;
import pt.ipp.estg.doa.store.excpetion.ManagerValidationException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public abstract class AbstractManager <T extends Entity> implements CrudManager<T> {

    Persistable<T> repository;

    public AbstractManager(Persistable<T> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return this.repository.loadData();
    }

    @Override
    public T findById(int id) throws ManagerValidationException {
        List<T> result = findAll();
        return result.stream()
                .filter(entity -> entity.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ManagerValidationException("ID not found: " + id));
    }

    @Override
    public T add(T entity) throws ManagerValidationException {
        List<T> result = findAll();

        // nextId
        Optional<T> nextId = result.stream().max(Comparator.comparing(Entity::getId));
        nextId.ifPresentOrElse(
                maxId -> entity.setId(maxId.getId() + 1),
                () -> entity.setId(1)
        );

        validate(entity);
        result.add(entity);
        this.repository.updateData(result);
        return entity;
    }

    public abstract void validate(T entity) throws ManagerValidationException;

    @Override
    public T update(int id, Dto dto) throws ManagerValidationException {
        List<T> result = findAll();

        T entity = result.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ManagerValidationException(
                                "ID not found for update (" + dto.getClass().getSimpleName() + "): " + id));

        entity.update(dto);
        this.repository.updateData(result);
        return entity;
    }

    @Override
    public void delete(int id) throws ManagerValidationException {
        List<T> result = findAll();
        T entity = result.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ManagerValidationException("ID not found for delete: " + id));

        result.remove(entity);
        this.repository.updateData(result);
    }
}
