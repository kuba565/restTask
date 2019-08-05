package pl.kuba565.validator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import pl.kuba565.TestBed;
import pl.kuba565.repository.WorkerRepository;

public class WorkerValidatorTest extends TestBed {
    @Autowired
    private WorkerRepository workerRepository;

    @Test
    public void shouldValidateNonExistentWorker() {
        //given
        WorkerValidator workerValidator = new WorkerValidator(workerRepository);
        final String expected = "Error in object '': codes [Worker with id = 999999 does not exist!]; arguments []; default message [null]";

        //when
        Errors errors = workerValidator.validateOnDelete(999999L);

        //then
        Assertions.assertEquals(expected, errors.getAllErrors().get(0).toString());
    }

}