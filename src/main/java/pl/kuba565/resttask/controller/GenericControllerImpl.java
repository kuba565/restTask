package pl.kuba565.resttask.controller;

import org.springframework.web.bind.annotation.*;
import pl.kuba565.resttask.dto.BaseDto;
import pl.kuba565.resttask.model.BaseModel;
import pl.kuba565.resttask.service.GenericService;
import pl.kuba565.resttask.transformer.dto.GenericDtoTransformerImpl;
import pl.kuba565.resttask.transformer.model.GenericModelTransformerImpl;

import javax.validation.constraints.NotNull;
import java.util.List;

import static pl.kuba565.resttask.util.StringUtil.ID;

public abstract class GenericControllerImpl<T extends BaseModel, Y extends BaseDto> implements GenericController<Y> {

    public GenericControllerImpl(GenericService<T> genericService,
                                 GenericDtoTransformerImpl<Y, T> genericDtoTransformer,
                                 GenericModelTransformerImpl<T, Y> genericModelTransformer) {
    }

    @GetMapping(ID)
    @ResponseBody
    public abstract Y findById(@PathVariable(value = "id") @NotNull Long id);

    @GetMapping()
    @ResponseBody
    public abstract List<Y> findAll();

    @PostMapping()
    @ResponseBody
    public abstract void create(Y dto);

    @PutMapping()
    @ResponseBody
    public abstract Y update(Y dto);

    @DeleteMapping(ID)
    @ResponseBody
    public abstract void deleteById(@PathVariable(value = "id") @NotNull Long id);
}
