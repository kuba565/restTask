package pl.kuba565.resttask.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.AbstractTest;
import pl.kuba565.resttask.dto.CarDto;
import pl.kuba565.resttask.dto.LogDto;
import pl.kuba565.resttask.exception.ValidationException;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.model.Log;
import pl.kuba565.resttask.service.CarServiceImpl;
import pl.kuba565.resttask.transformer.model.CarTransformerImpl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.kuba565.util.CarDtoAssertionUtil.assertCarDtoFetching;
import static pl.kuba565.util.CarDtoAssertionUtil.assertCarDtosFetching;

public class CarControllerImplTest extends AbstractTest {
    @Autowired
    private CarController carController;

    @Autowired
    private CarServiceImpl carServiceImpl;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CarTransformerImpl carTransformerImpl;

    private CarDto exampleCarDto;

    private List<CarDto> exampleCarDtos;

    private Long carId;

    @BeforeEach
    public void init() {
        exampleCarDtos = new ArrayList<>();
        List<Car> exampleCars = carServiceImpl.findAll();
        if (exampleCars.isEmpty()) {
            carServiceImpl.create(new Car(new Log("init"), 1221, 4, "1234"));
            exampleCars = carServiceImpl.findAll();
        }
        exampleCars.forEach(car -> exampleCarDtos.add(carTransformerImpl.apply(car)));
        Car exampleCar = exampleCars.get(0);
        exampleCarDto = carTransformerImpl.apply(exampleCar);
        carId = exampleCarDto.getId();
    }

    @Test
    public void shouldFindById() {
        //when
        CarDto carDto = carController.findById(carId);

        //then
        assertCarDtoFetching(exampleCarDto, carDto);
    }

    @Test
    public void shouldFindAll() {
        //when
        List<CarDto> carDtos = carController.findAll();

        //then
        assertCarDtosFetching(exampleCarDtos, carDtos);
    }

    @Test
    public void shouldUpdateWithoutLogField() {
        //given
        final CarDto newCarDto = new CarDto(carId, 2333, 2, "ASF123");

        //when
        carController.update(newCarDto);

        //then
        assertCarDtoFetching(newCarDto, carTransformerImpl.apply(carServiceImpl.findById(carId)));
    }


    @Test
    public void shouldUpdateWithLogField() {
        //given
        final CarDto expectedCar = new CarDto(carId, 2333, 2, "ASF123", new LogDto(1L, "aa"));

        //when
        carController.update(expectedCar);

        //then
        assertCarDtoFetching(expectedCar, carTransformerImpl.apply(carServiceImpl.findById(carId)));
    }

    @Test
    public void shouldCreate() {
        //given
        final CarDto expectedCar = new CarDto(2333, 2, "testValue", new LogDto("test"));

        //when
        carController.create(expectedCar);

        //then
        final Long id = entityManager
                .createQuery("SELECT c.id FROM Car c WHERE c.registrationNumber = :testValue", Long.class)
                .setParameter("testValue", "testValue")
                .getSingleResult();
        assertCarDtoFetching(expectedCar, carTransformerImpl.apply(carServiceImpl.findById(id)));
    }

    @Test
    public void shouldDeleteById() {
        //when
        carController.deleteById(carId);

        //then
        assertThrows(NoResultException.class, () -> carServiceImpl.findById(carId));
    }

    @Test
    public void shouldValidateNonExistentIdOnDelete() {
        //then
        assertThrows(ValidationException.class, () -> carController.deleteById(-1L));
    }

    @Test
    public void shouldNotCreateUnvalidated() {
        //given
        final CarDto expectedCar = new CarDto(2333, 2, "testValue");

        //then
        assertThrows(ValidationException.class, () -> carController.create(expectedCar));
    }
}