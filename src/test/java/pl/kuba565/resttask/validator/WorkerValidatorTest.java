package pl.kuba565.resttask.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import pl.kuba565.AbstractTest;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.model.Log;
import pl.kuba565.resttask.model.Worker;
import pl.kuba565.resttask.repository.WorkerRepositoryImpl;
import pl.kuba565.resttask.service.WorkerServiceImpl;
import pl.kuba565.resttask.transformer.model.WorkerTransformerImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkerValidatorTest extends AbstractTest {
    @Autowired
    private WorkerRepositoryImpl workerRepositoryImpl;

    @Autowired
    private WorkerServiceImpl workerService;

    @Autowired
    private WorkerValidator workerValidator;

    @Autowired
    private WorkerTransformerImpl workerTransformer;

    private Long workerId;

    @BeforeEach
    public void init() {
        List<Worker> exampleWorkers = workerService.findAll();
        if (exampleWorkers.isEmpty()) {
            workerService.create(new Worker(new Car(new Log("test")), "1221", "6", "1234"));
            exampleWorkers = workerService.findAll();
        }
        workerId = exampleWorkers.get(0).getId();
    }

    @Test
    public void shouldValidateNonExistentWorker() {
        //given
        final String expected = "Error in object '': codes [Worker with id = -1 does not exist!]; arguments []; default message [null]";

        //when
        Errors errors = workerValidator.validateOnDelete(-1L);

        //then
        assertEquals(expected, errors.getAllErrors().get(0).toString());
    }

    @Test
    public void shouldValidateExistingWorker() {
        //when
        Errors errors = workerValidator.validateOnDelete(workerId);

        //then
        assertEquals(0, errors.getAllErrors().size());
    }

}