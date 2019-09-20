package pl.kuba565.resttask.repository;

import graphql.schema.DataFetchingEnvironment;

import java.util.List;

public interface GenericRepository<T> {
    T update(T entity);

    void create(T entity);

    List<T> findAll();

    void deleteById(Long id);

    boolean exists(Long id);

    T findById(Long id);
}
