package pl.kuba565.resttask.controller;

import org.springframework.web.bind.annotation.PathVariable;
import pl.kuba565.resttask.dto.BaseDto;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface GenericController<Y extends BaseDto> {
    Y findById(@PathVariable(value = "id") @NotNull Long id);

    List<Y> findAll();

    void create(Y dto);

    Y update(Y dto);

    void deleteById(@PathVariable(value = "id") @NotNull Long id);
}
