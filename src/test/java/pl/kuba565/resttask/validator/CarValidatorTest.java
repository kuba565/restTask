package pl.kuba565.resttask.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import pl.kuba565.AbstractTest;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.model.Log;
import pl.kuba565.resttask.model.Worker;
import pl.kuba565.resttask.repository.CarRepositoryImpl;
import pl.kuba565.resttask.service.CarServiceImpl;
import pl.kuba565.resttask.service.WorkerServiceImpl;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CarValidatorTest extends AbstractTest {
    @Autowired
    private CarRepositoryImpl carRepository;

    @Autowired
    private CarValidator carValidator;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private WorkerServiceImpl workerService;

    @Autowired
    private CarServiceImpl carService;

    private Long assignedCar;

    private Long unassignedCar;

    @BeforeEach
    public void init() {
        List<Worker> exampleWorkers = workerService.findAll();
        if (exampleWorkers.isEmpty()) {
            workerService.create(new Worker(new Car(new Log("test"), 1221, 4, "1234"), "", "", ""));
            carService.create(new Car(new Log("test"), 111, 2, "testValue"));
            assignedCar = entityManager
                    .createQuery("SELECT c.id FROM Car c WHERE c.registrationNumber = :testValue", Long.class)
                    .setParameter("testValue", "1234")
                    .getSingleResult();
            unassignedCar = entityManager
                    .createQuery("SELECT c.id FROM Car c WHERE c.registrationNumber = :testValue", Long.class)
                    .setParameter("testValue", "testValue")
                    .getSingleResult();
        } else {
            unassignedCar = 1L;
            assignedCar = 2L;
        }
    }

    @Test
    public void shouldValidateNonExistentCar() {
        //given
        final String expected = "Error in object '': codes [Car with id = -1 does not exist!]; arguments []; default message [null]";

        //when
        Errors errors = carValidator.validateOnDelete(-1L);

        //then
        assertEquals(expected, errors.getAllErrors().get(0).toString());
    }

    @Test
    public void shouldValidateExistingCar() {
        //when
        Errors errors = carValidator.validateOnDelete(unassignedCar);

        //then
        assertEquals(0, errors.getAllErrors().size());
    }

    @Test
    public void shouldValidateAssignedCar() {
        //given
        final String expected = "Error in object '': codes [Car with given id has 1 worker(s) assigned]; arguments []; default message [null]";

        //when
        Errors errors = carValidator.validateOnDelete(assignedCar);

        //then
        assertEquals(expected, errors.getAllErrors().get(0).toString());
    }

    @Test
    public void shouldValidateNonAssignedCar() {
        //given
        final String expected = "Error in object '': codes [Car with id = 2 does not exist!]; arguments []; default message [null]";

        //when
        Errors errors = carValidator.validateOnDelete(unassignedCar);

        //then
        assertEquals(0, errors.getAllErrors().size());
    }
}