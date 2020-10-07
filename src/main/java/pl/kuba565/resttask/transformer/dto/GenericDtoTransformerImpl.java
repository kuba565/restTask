package pl.kuba565.resttask.transformer.dto;

import pl.kuba565.resttask.dto.BaseDto;
import pl.kuba565.resttask.model.BaseModel;

public abstract class GenericDtoTransformerImpl<Y extends BaseDto, T extends BaseModel> implements GenericDtoTransformer<Y, T> {
}
