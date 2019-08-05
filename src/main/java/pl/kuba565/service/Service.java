package pl.kuba565.service;

import java.util.List;

public interface Service<T> {
    T update(T newT);

    Long create(T newT);

    void deleteById(Long id);

    List<T> findAll();

    T findById(Long id);
}
