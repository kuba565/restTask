package pl.kuba565.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import pl.kuba565.TestBed;
import pl.kuba565.model.Car;
import pl.kuba565.model.Worker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class WorkerRepositoryTest extends TestBed {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    @DirtiesContext
    public void shouldCreateWorker() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        WorkerRepository workerRepository = new WorkerRepository(entityManager);

        final Worker worker = new Worker("12345678901", "John", "Hancock");

        //when
        transaction.begin();
        final Long workerId = workerRepository.create(worker);
        transaction.commit();

        //then
        Worker result = workerRepository.findById(workerId);
        worker.setId(workerId);
        Assertions.assertEquals(worker, result);
    }


    @Test
    @DirtiesContext
    public void shouldUpdateWorker() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        WorkerRepository workerRepository = new WorkerRepository(entityManager);

        final Worker worker = new Worker(1L, "12345678901", "John", "Hancock");

        //when
        worker.setName("Marcin");

        transaction.begin();
        final Long workerId = workerRepository.update(worker).getId();
        transaction.commit();

        //then
        Assertions.assertEquals(worker, workerRepository.findById(workerId));
    }


    @Test
    @DirtiesContext
    public void shouldDeleteWorker() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        WorkerRepository workerRepository = new WorkerRepository(entityManager);
        final Long workerId = 1L;

        //when
        transaction.begin();
        workerRepository.deleteById(workerId);
        transaction.commit();

        //then
        Assertions.assertThrows(NoResultException.class, () -> workerRepository.findById(workerId));
    }

    @Test
    @DirtiesContext
    public void shouldFindAllCars() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        WorkerRepository workerRepository = new WorkerRepository(entityManager);

        final List<Worker> expected = List.of(
                new Worker(1L, new Car(2L, 1500, 5, "PO6HH12", "test"), "12345678901", "Jakub", "Kąkolewski"),
                new Worker(2L, new Car(2L, 1500, 5, "PO6HH12", "test"), "12345678902", "Adam", "Nowak"),
                new Worker(3L, "12342678902", "Marian", "Nowak")
        );

        //when
        List<Worker> result = workerRepository.findAll();

        //then
        Assertions.assertEquals(expected, result);
    }
}