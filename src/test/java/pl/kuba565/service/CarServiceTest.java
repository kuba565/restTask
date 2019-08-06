package pl.kuba565.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.TestBed;
import pl.kuba565.exception.ValidationException;
import pl.kuba565.model.Car;
import pl.kuba565.repository.CarRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class CarServiceTest extends TestBed {
    @Autowired
    private CarService carService;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void shouldFindAllCars() {
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
        final Car car = new Car(11, 5, "AVBASD2");

        //when
        Long carId = carService.create(car);

        //then
        car.setId(carId);
        Assertions.assertEquals(car, entityManager.createQuery("Select c from Car c where c.id = :carId")
                .setParameter("carId", carId)
                .getSingleResult()
        );
    }


    @Test
    public void shouldUpdateCar() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        final Car car = new Car(1L, 11, 5, "AVBASD2");

        //when
        car.setNumberOfSeats(101);
        Long carId = carService.update(car).getId();

        //then
        Assertions.assertEquals(car, entityManager.createQuery("Select new Car(c.id, c.weight, c.numberOfSeats, c.registrationNumber) from Car c where c.id = :carId")
                .setParameter("carId", carId).getSingleResult()
        );
    }

    @Test
    public void shouldDeleteCar() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        CarRepository carRepository = new CarRepository(entityManager);
        final Long carId = 3L;

        //when
        transaction.begin();
        carService.deleteById(carId);
        transaction.commit();

        //then
        Assertions.assertThrows(NoResultException.class,
                () -> carRepository.findById(carId)
        );
    }

    @Test
    public void shouldThrowValidationExceptionWhenDeleteCarAssignedToWorker() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Long carId = entityManager.createQuery("SELECT c.id FROM Car c JOIN Worker w ON w.car.id=c.id", Long.class)
                .getSingleResult();

        //then
        Assertions.assertThrows(ValidationException.class, () -> carService.deleteById(carId));
    }
}