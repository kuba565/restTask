package pl.kuba565.resttask.transformer.dto;

import pl.kuba565.resttask.dto.BaseDto;
import pl.kuba565.resttask.model.BaseModel;

public interface GenericDtoTransformer<Y extends BaseDto, T extends BaseModel> {
    T apply(Y model);
}
