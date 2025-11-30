package pt.ipp.estg.doa.store.utils;

import pt.ipp.estg.doa.store.dto.Dto;

import java.util.List;

public class AbstractManager <T extends Entity> implements CrudManager<T> {

    Persistable<T> repository;

    public AbstractManager(Persistable<T> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return this.repository.loadData();
    }

    @Override
    public T findById(int id) {
        List<T> result = findAll();
        return result.stream()
                .filter(entity -> entity.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void add(T entity) {
        List<T> result = findAll();
        entity.setId(result.size() +1);
        result.add(entity);
        this.repository.updateData(result);
    }

    @Override
    public void update(int id, Dto dto) {
        List<T> result = findAll();

        T entity = result.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Registro nao encontrado: " + id);
                    return null;
                });

        if (entity != null) {
            entity.update(dto);
            this.repository.updateData(result);
        }
    }

    @Override
    public void delete(int id) {
        List<T> result = findAll();
        T entity = result.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Regsitro nao encontrado: " + id);
                    return null;
                });

        if (entity != null) {
            result.remove(entity);
            this.repository.updateData(result);
        }
    }
}
