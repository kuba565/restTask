package pl.kuba565.resttask.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.kuba565.AbstractTest;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.repository.CarRepositoryImpl;
import pl.kuba565.resttask.validator.CarValidator;

import static org.mockito.Mockito.times;

public class CarServiceImplTest extends AbstractTest {
    private CarServiceImpl carService;

    @Mock
    private CarRepositoryImpl carRepository;

    @Mock
    private CarValidator carValidator;

    @BeforeEach
    public void init() {
        carService = new CarServiceImpl(carRepository, carValidator);
    }

    @Test
    public void shouldCallRepositoryFindAll() {
        //when
        carService.findAll();

        //then
        Mockito.verify(carRepository, times(1)).findAll();
    }

    @Test
    public void shouldCallRepositoryFindById() {
        //given
        final long id = 1L;

        //when
        carService.findById(id);

        //then
        Mockito.verify(carRepository, times(1)).findById(id);
    }

    @Test
    public void shouldCallRepositoryCreate() {
        //given
        final Car car = new Car();

        //when
        carService.create(car);

        //then
        Mockito.verify(carRepository, times(1)).create(car);
    }

    @Test
    public void shouldCallRepositoryUpdate() {
        //given
        final Car car = new Car();

        //when
        carService.update(car);

        //then
        Mockito.verify(carRepository, times(1)).update(car);
    }

    @Test
    public void shouldCallRepositoryDelete() {
        //given
        final long id = 1L;

        //when
        carService.deleteById(id);

        //then
        Mockito.verify(carRepository, times(1)).deleteById(id);
    }

    @Test
    public void shouldCallValidatorValidateOnDelete() {
        //given
        final long id = -1L;

        //when
        carService.deleteById(id);

        //then
        Mockito.verify(carValidator, times(1)).validateOnDelete(id);
    }
}