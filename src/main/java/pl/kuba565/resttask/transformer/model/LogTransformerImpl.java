package pl.kuba565.resttask.transformer.model;

import org.hibernate.Hibernate;
import pl.kuba565.resttask.dto.LogDto;
import pl.kuba565.resttask.model.Log;

import java.util.function.Function;

public class LogTransformerImpl extends GenericModelTransformerImpl<Log, LogDto> implements Function<Log, LogDto> {
    @Override
    public LogDto apply(Log log) {
        LogDto.LogDtoBuilder logDtoBuilder = LogDto.builder();
        if (Hibernate.isInitialized(log) && log != null) {
            logDtoBuilder.value(log.getValue());
            if (log.getId() != null) {
                logDtoBuilder.id(log.getId());
            }
            return logDtoBuilder
                    .build();
        }
        return null;
    }
}
