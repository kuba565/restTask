package pl.kuba565.validator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import pl.kuba565.TestBed;
import pl.kuba565.repository.CarRepository;

public class CarValidatorTest extends TestBed {
    @Autowired
    private CarRepository carRepository;

    @Test
    public void shouldValidateNonExistentCar() {
        //given
        CarValidator carValidator = new CarValidator(carRepository);
        final String expected = "Error in object '': codes [Car with id = 9999 does not exist!]; arguments []; default message [null]";

        //when
        Errors errors = carValidator.validateOnDelete(9999L);

        //then
        Assertions.assertEquals(expected, errors.getAllErrors().get(0).toString());
    }

    @Test
    public void shouldValidateAssignedCar() {
        //given
        CarValidator carValidator = new CarValidator(carRepository);
        final String expected = "Error in object '': codes [Car with given id has 2 worker(s) assigned]; arguments []; default message [null]";

        //when
        Errors errors = carValidator.validateOnDelete(2L);

        //then
        Assertions.assertEquals(expected, errors.getAllErrors().get(0).toString());
    }
}