package pl.kuba565.controller;

import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface Controller<T> {
    T findById(@PathVariable(value = "id") @NotNull Long id);

    List<T> findAll();

    void post(T newT);

    T put(T newT);

    void deleteById(@PathVariable(value = "id") @NotNull Long id);
}
