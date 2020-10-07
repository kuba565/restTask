package pl.kuba565.resttask.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.kuba565.AbstractTest;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.model.Log;
import pl.kuba565.resttask.model.Worker;
import pl.kuba565.resttask.repository.WorkerRepositoryImpl;
import pl.kuba565.resttask.validator.WorkerValidator;

import static org.mockito.Mockito.times;

public class WorkerServiceImplTest extends AbstractTest {
    private WorkerServiceImpl workerServiceImpl;

    @Mock
    private WorkerRepositoryImpl workerRepositoryImpl;

    @Mock
    private WorkerValidator workerValidator;

    @BeforeEach
    public void init() {
        workerServiceImpl = new WorkerServiceImpl(workerRepositoryImpl, workerValidator);
    }

    @Test
    public void shouldCallRepositoryFindAll() {
        //when
        workerServiceImpl.findAll();

        //then
        Mockito.verify(workerRepositoryImpl, times(1)).findAll();
    }

    @Test
    public void shouldCallRepositoryFindById() {
        //when
        workerServiceImpl.findById(1L);

        //then
        Mockito.verify(workerRepositoryImpl, times(1)).findById(1L);
    }

    @Test
    public void shouldCallRepositoryCreate() {
        //given
        final Worker worker = new Worker((new Car(new Log("test"))), "1", "g", "a");

        //when
        workerServiceImpl.create(worker);

        //then
        Mockito.verify(workerRepositoryImpl, times(1)).create(worker);
    }

    @Test
    public void shouldCallRepositoryUpdate() {
        //given
        final Worker worker = new Worker(1L, "test", "test", "test", new Car(new Log("test")));

        //when
        workerServiceImpl.update(worker);

        //then
        Mockito.verify(workerRepositoryImpl, times(1)).update(worker);
    }

    @Test
    public void shouldCallRepositoryDelete() {
        //when
        workerServiceImpl.deleteById(1L);

        //then
        Mockito.verify(workerRepositoryImpl, times(1)).deleteById(1L);
    }

    @Test
    public void shouldCallValidatorValidateOnDelete() {
        //when
        workerServiceImpl.deleteById(-1L);
        //then
        Mockito.verify(workerValidator, times(1)).validateOnDelete(-1L);
    }
}