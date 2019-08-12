package pl.kuba565.controller;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.TestBed;
import pl.kuba565.Util.EntityManagerUtil;
import pl.kuba565.model.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

public class CarControllerTest extends TestBed {
    @Autowired
    private CarController carController;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void shouldFindById() {
        //given
        final Long carId = 1L;
        final Car expectedCar = new Car(carId, 1500, 5, "PO6HH12");

        //when
        Car car = carController.findById(carId);

        //then
        Assertions.assertEquals(expectedCar, car);
    }

    @Test
    public void shouldFindAll() {
        //given
        final List<Car> expectedCars = List.of(new Car(1L, 1500, 5, "PO6HH12"),
                new Car(2L, 1500, 5, "PO6HH12"),
                new Car(3L, 500, 4, "PO121TJ"));

        //when
        List<Car> cars = carController.findAll();

        //then
        Assertions.assertEquals(expectedCars, cars);
    }

    @Test
    public void shouldPutWithoutLogField() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        final Long carId = 1L;
        final Car expectedCar = new Car(carId, 2333, 2, "ASF123");

        //when
        carController.put(expectedCar);

        //then
        Assertions.assertEquals(expectedCar, EntityManagerUtil.getCarById(entityManager, carId));
    }

    @Test
    public void shouldPost() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        final Car expectedCar = new Car(2333, 2, "ASF123", null);

        //when
        Long carId = carController.post(expectedCar);

        //then
        expectedCar.setId(carId);
        Assertions.assertEquals(expectedCar, EntityManagerUtil.getCarById(entityManager, carId));
    }

    @Test
    public void shouldDeleteById() {
        //when
        final Long carId = 1L;
        carController.deleteById(carId);

        //then
        Assertions.assertThrows(NoResultException.class, () -> EntityManagerUtil.getCarById(entityManager, carId));
    }
}