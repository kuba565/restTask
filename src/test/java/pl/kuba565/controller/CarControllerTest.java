package pl.kuba565.controller;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.TestBed;
import pl.kuba565.model.Car;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;


public class CarControllerTest extends TestBed {
    @Autowired
    private CarController carController;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(carController);
    }

    @Test
    public void shouldFindById() {
        //given
        final Long carId = 1L;
        final Car expectedCar = new Car(carId, 1500, 5, "PO6HH12", null);

        //when
        Car car = carController.findById(carId);

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
        final Long carId = 1L;
        final Car expectedCar = new Car(carId, 2333, 2, "ASF123", null);

        //when
        carController.put(expectedCar);

        //then
        Assertions.assertEquals(expectedCar, entityManager.createQuery("SELECT new Car(c.id, c.weight, c.numberOfSeats, c.registrationNumber) FROM Car c WHERE c.id = :carId").setParameter("carId", carId).getSingleResult());
    }

    @Test
    public void shouldPost() {
        //given
        final Car expectedCar = new Car(2333, 2, "ASF123", null);

        //when
        Long carId = carController.post(expectedCar);

        //then
        Assertions.assertEquals(expectedCar, entityManager.createQuery("SELECT new Car(c.weight, c.numberOfSeats, c.registrationNumber) FROM Car c WHERE c.id = :carId").setParameter("carId", carId).getSingleResult());
    }

    @Test
    public void shouldDeleteById() {
        //when
        final Long carId = 1L;
        carController.deleteById(carId);

        //then
        Assertions.assertThrows(NoResultException.class, () -> entityManager.createQuery("SELECT c FROM Car c WHERE c.id = :carId").setParameter("carId", carId).getSingleResult());
    }
}