package pl.kuba565.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.TestBed;
import pl.kuba565.model.Car;
import pl.kuba565.model.Log;

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
        Assertions.assertEquals(car, getCarById(entityManager, carId));
    }

    @Test
    public void shouldUpdateCarWithoutLogField() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CarRepository carRepository = new CarRepository(entityManager);
        final Car car = new Car(1L, 11, 5, "123451");

        //when
        transaction.begin();
        final Long carId = carRepository.update(car).getId();
        transaction.commit();

        //then
        Assertions.assertEquals(car, getCarById(entityManager, carId));
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
        Assertions.assertThrows(NoResultException.class, () -> getCarById(entityManager, carId));
    }

    @Test
    public void shouldFindAllCarsWithoutLogField() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CarRepository carRepository = new CarRepository(entityManager);

        final List<Car> expected = List.of(
                new Car(1L, 1500, 5, "PO6HH12", null),
                new Car(2L, 1500, 5, "PO6HH12", null),
                new Car(3L, 500, 4, "PO121TJ", null)
        );

        //when
        List<Car> result = carRepository.findAll();

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void shouldFindCarWithoutLogField() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CarRepository carRepository = new CarRepository(entityManager);
        long carId = 1L;
        final Car expected = new Car(carId, 1500, 5, "PO6HH12", null);

        //when
        Car result = carRepository.findById(carId);

        //then
        Assertions.assertEquals(expected, result);
    }

    private Object getCarById(EntityManager entityManager, Long carId) {
        return entityManager.createQuery("FROM Car c WHERE c.id = :carId").setParameter("carId", carId).getSingleResult();
    }
}