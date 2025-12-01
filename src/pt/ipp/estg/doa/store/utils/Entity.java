package pt.ipp.estg.doa.store.utils;

import pt.ipp.estg.doa.store.dto.Dto;
import pt.ipp.estg.doa.store.excpetion.ManagerValidationException;

public abstract class Entity implements Identifiable {

    private int id;

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public abstract <T extends Dto> void update(T dto) throws ManagerValidationException;
}
