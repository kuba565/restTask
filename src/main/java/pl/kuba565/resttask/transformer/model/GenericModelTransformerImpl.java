package pl.kuba565.resttask.transformer.model;

import pl.kuba565.resttask.dto.BaseDto;
import pl.kuba565.resttask.model.BaseModel;

public abstract class GenericModelTransformerImpl<T extends BaseModel, Y extends BaseDto> implements GenericModelTransformer<T, Y> {
}
