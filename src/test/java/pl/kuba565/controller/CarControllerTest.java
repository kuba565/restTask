package pl.kuba565.controller;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.TestBed;
import pl.kuba565.model.Car;

import java.util.List;


public class CarControllerTest extends TestBed {
    @Autowired
    private CarController carController;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(carController);
    }

    @Test
    public void shouldFindById() {
        //given
        final Car expectedCar = new Car(1L, 1500, 5, "PO6HH12", null);

        //when
        Car car = carController.findById(1L);

        //then
        Assertions.assertEquals(expectedCar, car);
    }

    @Test
    public void shouldFindAll() {
        //given
        final List<Car> expectedCars = List.of(new Car(1L, 1500, 5, "PO6HH12", null),
                new Car(2L, 1500, 5, "PO6HH12", null),
                new Car(3L, 500, 4, "PO121TJ", null));

        //when
        List<Car> cars = carController.findAll();

        //then
        Assertions.assertEquals(expectedCars, cars);
    }

    @Test
    public void shouldPut() {
        //given
        final Car car = new Car(2333, 2, "ASF123", null);

        //when
        carController.post(car);

        //then
    }
}