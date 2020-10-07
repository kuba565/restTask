package pl.kuba565.resttask.transformer.dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.AbstractTest;
import pl.kuba565.resttask.dto.LogDto;
import pl.kuba565.resttask.model.Log;

import static org.junit.jupiter.api.Assertions.*;

class LogDtoTransformerImplTest extends AbstractTest {
    @Autowired
    private LogDtoTransformerImpl logDtoTransformer;

    @Test
    public void shouldTransformLogDtoToLog() {
        //given
        LogDto logDto = new LogDto(1L, "test");
        Log expectedLog = new Log(1L, "test");

        //when
        Log log = logDtoTransformer.apply(logDto);

        //then
        assertAll(
                () -> assertEquals(log.getId(), expectedLog.getId()),
                () -> assertEquals(log.getValue(), expectedLog.getValue())
        );
    }

    @Test
    public void shouldTransformLogDtoToLogWithNullFields() {
        //given
        LogDto logDto = new LogDto();

        //when
        Log log = logDtoTransformer.apply(logDto);

        //then
        assertAll(
                () -> assertNull(log.getId()),
                () -> assertNull(log.getValue())
        );
    }
}