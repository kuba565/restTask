package pl.kuba565.resttask.transformer.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.AbstractTest;
import pl.kuba565.resttask.dto.LogDto;
import pl.kuba565.resttask.model.Log;

import static org.junit.jupiter.api.Assertions.*;

public class LogTransformerImplTest extends AbstractTest {
    @Autowired
    private LogTransformerImpl logTransformer;

    @Test
    public void shouldTransformLogDtoToLog() {
        //given
        Log log = new Log(1L, "test");
        LogDto expectedLogDto = new LogDto(1L, "test");

        //when
        LogDto logDto = logTransformer.apply(log);

        //then
        assertAll(
                () -> assertEquals(logDto.getId(), expectedLogDto.getId()),
                () -> assertEquals(logDto.getValue(), expectedLogDto.getValue())
        );
    }

    @Test
    public void shouldTransformLogToLogDtoWithNullFields() {
        //given
        Log log = new Log();

        //when
        LogDto logDto = logTransformer.apply(log);

        //then
        assertAll(
                () -> assertNull(logDto.getId()),
                () -> assertNull(logDto.getValue())
        );
    }
}