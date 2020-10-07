package pl.kuba565.resttask.transformer.model;

import pl.kuba565.resttask.dto.BaseDto;
import pl.kuba565.resttask.model.BaseModel;

public interface GenericModelTransformer<T extends BaseModel, Y extends BaseDto> {
    Y apply(T model);
}
