package pl.kuba565.service;

import org.h2.mvstore.db.TransactionStore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.TestBed;
import pl.kuba565.exception.ValidationException;
import pl.kuba565.model.Car;
import pl.kuba565.model.Log;
import pl.kuba565.repository.CarRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.transaction.Transaction;
import java.util.List;

public class CarServiceTest extends TestBed {
    @Autowired
    private CarService carService;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void shouldFindAllCarsWithoutLogField() {
        //given
        final List<Car> expected = List.of(
                new Car(1L, 1500, 5, "PO6HH12"),
                new Car(2L, 1500, 5, "PO6HH12"),
                new Car(3L, 500, 4, "PO121TJ")
        );

        //when
        List<Car> result = carService.findAll();

        //then
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void shouldCreateCar() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        final Car car = new Car(11, 5, "AVBASD2");

        //when
        transaction.begin();
        Long carId = carService.create(car);
        transaction.commit();

        //then
        car.setId(carId);
        Assertions.assertEquals(car, getCarById(entityManager, carId)
        );
    }


    @Test
    public void shouldUpdateCarWithoutLogField() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        final Car car = new Car(1L, 11, 5, "AVBASD2");

        //when
        car.setNumberOfSeats(101);
        transaction.begin();
        Long carId = carService.update(car).getId();
        transaction.commit();

        //then
        Assertions.assertEquals(car, getCarById(entityManager, carId));
    }

    @Test
    public void shouldDeleteCar() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        final Long carId = 3L;

        //when
        transaction.begin();
        carService.deleteById(carId);
        transaction.commit();

        //then
        Assertions.assertThrows(NoResultException.class,
                () -> getCarById(entityManager, carId)
        );
    }

    @Test
    public void shouldThrowValidationExceptionWhenDeleteCarAssignedToWorker() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Long carId = entityManager.createQuery("SELECT c.id FROM Car c JOIN Worker w ON w.car.id=c.id", Long.class)
                .getSingleResult();

        //when
        //then
        Assertions.assertThrows(ValidationException.class, () -> carService.deleteById(carId));
    }

    private Object getCarById(EntityManager entityManager, Long carId) {
        return entityManager.createQuery("from Car c where c.id = :carId").setParameter("carId", carId).getSingleResult();
    }
}