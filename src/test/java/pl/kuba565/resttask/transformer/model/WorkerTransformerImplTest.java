package pl.kuba565.resttask.transformer.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.AbstractTest;
import pl.kuba565.resttask.dto.CarDto;
import pl.kuba565.resttask.dto.LogDto;
import pl.kuba565.resttask.dto.WorkerDto;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.model.Log;
import pl.kuba565.resttask.model.Worker;

import static org.junit.jupiter.api.Assertions.*;

public class WorkerTransformerImplTest extends AbstractTest {
    @Autowired
    private WorkerTransformerImpl workerTransformer;

    @Test
    public void shouldTransformWorkerToWorkerDto() {
        //given
        WorkerDto expectedWorkerDto = new WorkerDto(new CarDto(1, 5, "11", new LogDto("test")), "a", "aa", "aaa");
        Worker Worker = new Worker(new Car(new Log("test"), 1, 5, "11"), "a", "aa", "aaa");

        //when
        WorkerDto workerDto = workerTransformer.apply(Worker);

        //then
        assertAll(
                () -> assertNull(workerDto.getId()),
                () -> assertEquals(expectedWorkerDto.getCarDto().getNumberOfSeats(), workerDto.getCarDto().getNumberOfSeats()),
                () -> assertEquals(expectedWorkerDto.getCarDto().getRegistrationNumber(), workerDto.getCarDto().getRegistrationNumber()),
                () -> assertEquals(expectedWorkerDto.getCarDto().getWeight(), workerDto.getCarDto().getWeight()),
                () -> assertEquals(expectedWorkerDto.getCarDto().getLogDto().getId(), workerDto.getCarDto().getLogDto().getId()),
                () -> assertEquals(expectedWorkerDto.getCarDto().getLogDto().getValue(), workerDto.getCarDto().getLogDto().getValue())
        );
    }

    @Test
    public void shouldTransformWorkerToWorkerDtoWithNullLog() {
        //given
        WorkerDto expectedWorkerDto = new WorkerDto(new CarDto(1, 5, "11"), "a", "aa", "aaa");
        Worker Worker = new Worker(new Car(1, 5, "11"), "a", "aa", "aaa");

        //when
        WorkerDto workerDto = workerTransformer.apply(Worker);

        //then
        assertAll(
                () -> assertNull(workerDto.getId()),
                () -> assertEquals(workerDto.getSurname(), expectedWorkerDto.getSurname()),
                () -> assertEquals(workerDto.getName(), expectedWorkerDto.getName()),
                () -> assertEquals(workerDto.getPesel(), expectedWorkerDto.getPesel()),
                () -> assertEquals(workerDto.getCarDto().getNumberOfSeats(), expectedWorkerDto.getCarDto().getNumberOfSeats()),
                () -> assertEquals(workerDto.getCarDto().getRegistrationNumber(), expectedWorkerDto.getCarDto().getRegistrationNumber()),
                () -> assertEquals(workerDto.getCarDto().getWeight(), expectedWorkerDto.getCarDto().getWeight()),
                () -> assertNull(workerDto.getCarDto().getLogDto())
        );
    }

    @Test
    public void shouldTransformCarDtoToCarWithAllFieldsNull() {
        //given
        Worker worker = new Worker();

        //when
        WorkerDto workerDto = workerTransformer.apply(worker);

        //then
        assertAll(
                () -> assertNotNull(workerDto),
                () -> assertNull(workerDto.getId()),
                () -> assertNull(workerDto.getSurname()),
                () -> assertNull(workerDto.getName()),
                () -> assertNull(workerDto.getPesel()),
                () -> assertNull(workerDto.getCarDto())
        );
    }
}