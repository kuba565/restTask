package pl.kuba565.resttask.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.AbstractTest;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.model.Log;
import pl.kuba565.resttask.service.CarServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.kuba565.util.CarAssertionUtil.assertCarFetching;
import static pl.kuba565.util.CarAssertionUtil.assertCarsFetching;

@Transactional
public class CarRepositoryImplTest extends AbstractTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CarRepositoryImpl carRepository;

    @Autowired
    private CarServiceImpl carServiceImpl;

    private List<Car> exampleCars;

    private Car exampleCar;

    private Long carId;

    @BeforeEach
    public void init() {
        exampleCars = carServiceImpl.findAll();
        if (exampleCars.isEmpty()) {
            carServiceImpl.create(new Car(new Log("test"), 1221, 4, "1234"));
            exampleCars = carServiceImpl.findAll();
        }
        exampleCar = exampleCars.get(0);
        carId = exampleCar.getId();
    }

    @Test
    public void shouldCreateCar() {
        //given
        final Car newCar = new Car(new Log("test"), 11, 5, "AVBASD2");

        //when
        carRepository.create(newCar);

        //then
        carId = entityManager
                .createQuery("SELECT c.id FROM Car c WHERE c.registrationNumber = :registrationNumber", Long.class)
                .setParameter("registrationNumber", newCar.getRegistrationNumber())
                .getSingleResult();
        assertCarFetching(newCar, carServiceImpl.findById(carId));
    }

    @Test
    public void shouldUpdateCarWithoutLogField() {
        //given
        final Car car = new Car(1L, 11, 5, "123451", new Log("test"));

        //when
        final Long carId = carRepository.update(car).getId();

        //then
        assertCarFetching(car, carServiceImpl.findById(carId));
    }

    @Test
    public void shouldDeleteCar() {
        //when
        carRepository.deleteById(carId);

        //then
        assertThrows(NoResultException.class, () -> carServiceImpl.findById(carId));
    }

    @Test
    public void shouldFindAllCarsWithoutLogField() {
        //when
        List<Car> result = carRepository.findAll();

        //then
        assertCarsFetching(exampleCars, result);
    }

    @Test

    public void shouldFindCarWithoutLogField() {
        //when
        Car result = carRepository.findById(carId);

        //then
        assertCarFetching(exampleCar, result);
    }
}