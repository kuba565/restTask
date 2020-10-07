package pl.kuba565.resttask.validator;

import org.springframework.validation.Errors;

public interface GenericValidator<T> {
    Errors validateOnDelete(Long id);

    Errors validateOnCreate(T entity);
}
