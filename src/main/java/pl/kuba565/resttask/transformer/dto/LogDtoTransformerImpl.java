package pl.kuba565.resttask.transformer.dto;

import pl.kuba565.resttask.dto.LogDto;
import pl.kuba565.resttask.model.Log;

import java.util.function.Function;

public class LogDtoTransformerImpl extends GenericDtoTransformerImpl<LogDto, Log> implements Function<LogDto, Log> {

    @Override
    public Log apply(LogDto logDto) {
        return Log.builder()
                .id(logDto.getId())
                .value(logDto.getValue())
                .build();
    }
}
