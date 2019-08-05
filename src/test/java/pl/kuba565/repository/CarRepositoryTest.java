package pl.kuba565.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.TestBed;
import pl.kuba565.model.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class CarRepositoryTest extends TestBed {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void shouldCreateCar() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CarRepository carRepository = new CarRepository(entityManager);

        final Car car = new Car(11, 5, "AVBASD2");

        //when1
        transaction.begin();
        final Long carId = carRepository.create(car);
        transaction.commit();

        //then1
        car.setId(carId);
        Assertions.assertEquals(car, carRepository.findById(carId));
    }


    @Test
    public void shouldUpdateCar() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CarRepository carRepository = new CarRepository(entityManager);
        final Car car = new Car(1L, 11, 5, "AVBASD2");

        //when
        car.setRegistrationNumber("123451");
        transaction.begin();
        final Long carId = carRepository.update(car).getId();
        transaction.commit();

        //then
        Assertions.assertEquals(car, carRepository.findById(carId));
    }


    @Test
    public void shouldDeleteCar() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CarRepository carRepository = new CarRepository(entityManager);
        final Long carId = 1L;

        //when3
        transaction.begin();
        carRepository.deleteById(carId);
        transaction.commit();

        //then3
        Assertions.assertThrows(NoResultException.class, () -> carRepository.findById(carId));
    }

    @Test
    public void shouldFindAllCars() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CarRepository carRepository = new CarRepository(entityManager);

        final List<Car> expected = List.of(
                new Car(1L, 1500, 5, "PO6HH12"),
                new Car(2L, 1500, 5, "PO6HH12"),
                new Car(3L, 500, 4, "PO121TJ")
        );

        //when
        List<Car> result = carRepository.findAll();

        //then
        Assertions.assertEquals(expected, result);
    }
}