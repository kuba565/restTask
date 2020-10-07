package pl.kuba565.resttask.service;

import pl.kuba565.resttask.model.BaseModel;

import java.util.List;

public interface GenericService<T extends BaseModel> {
    T update(T entity);

    void create(T entity);

    void deleteById(Long id);

    List<T> findAll();

    T findById(Long id);
}
