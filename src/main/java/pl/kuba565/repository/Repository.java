package pl.kuba565.repository;

import java.util.List;

public interface Repository<T> {
    T update(T newT);

    Long create(T newT);

    List<T> findAll();

    void deleteById(Long id);

    Boolean checkIfExists(Long id);

    T findById(Long id);

    Long countRelationships(Long id);
}
