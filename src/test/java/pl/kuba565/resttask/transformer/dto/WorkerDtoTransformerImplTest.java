package pl.kuba565.resttask.transformer.dto;

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

class WorkerDtoTransformerImplTest extends AbstractTest {
    @Autowired
    private WorkerDtoTransformerImpl workerDtoTransformer;

    @Test
    public void shouldTransformWorkerDtoToWorker() {
        //given
        WorkerDto workerDto = new WorkerDto(new CarDto(1, 5, "11", new LogDto("test")), "a", "aa", "aaa");
        Worker expectedWorker = new Worker(new Car(new Log("test"), 1, 5, "11"), "a", "aa", "aaa");

        //when
        Worker worker = workerDtoTransformer.apply(workerDto);

        //then
        assertAll(
                () -> assertNull(worker.getCar().getId()),
                () -> assertEquals(expectedWorker.getCar().getNumberOfSeats(), worker.getCar().getNumberOfSeats()),
                () -> assertEquals(expectedWorker.getCar().getRegistrationNumber(), worker.getCar().getRegistrationNumber()),
                () -> assertEquals(expectedWorker.getCar().getWeight(), worker.getCar().getWeight()),
                () -> assertEquals(expectedWorker.getCar().getLog().getId(), worker.getCar().getLog().getId()),
                () -> assertEquals(expectedWorker.getCar().getLog().getValue(), worker.getCar().getLog().getValue())
        );
    }

    @Test
    public void shouldTransformWorkerDtoToWorkerWithNullLog() {
        //given
        WorkerDto workerDto = new WorkerDto(new CarDto(1, 5, "11"), "a", "aa", "aaa");
        Worker expectedWorker = new Worker(new Car(1, 5, "11"), "a", "aa", "aaa");

        //when
        Worker worker = workerDtoTransformer.apply(workerDto);

        //then
        assertAll(
                () -> assertNull(worker.getId()),
                () -> assertEquals(worker.getSurname(), expectedWorker.getSurname()),
                () -> assertEquals(worker.getName(), expectedWorker.getName()),
                () -> assertEquals(worker.getPesel(), expectedWorker.getPesel()),
                () -> assertEquals(worker.getCar().getNumberOfSeats(), expectedWorker.getCar().getNumberOfSeats()),
                () -> assertEquals(worker.getCar().getRegistrationNumber(), expectedWorker.getCar().getRegistrationNumber()),
                () -> assertEquals(worker.getCar().getWeight(), expectedWorker.getCar().getWeight()),
                () -> assertNull(worker.getCar().getLog())
        );
    }

    @Test
    public void shouldTransformCarDtoToCarWithAllFieldsNull() {
        //given
        WorkerDto workerDto = new WorkerDto();

        //when
        Worker worker = workerDtoTransformer.apply(workerDto);

        //then
        assertAll(
                () -> assertNotNull(worker),
                () -> assertNull(worker.getId()),
                () -> assertNull(worker.getSurname()),
                () -> assertNull(worker.getName()),
                () -> assertNull(worker.getPesel()),
                () -> assertNull(worker.getCar())
        );
    }
}