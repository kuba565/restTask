package pl.kuba565.repository;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
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
        worker.setId(workerId);
        Assertions.assertEquals(worker, getWorkerById(entityManager, workerId));
    }


    @Test
    public void shouldUpdateWorker() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        WorkerRepository workerRepository = new WorkerRepository(entityManager);

        final Worker worker = new Worker(1L, "12345678901", "Marcin", "Hancock");

        //when
        transaction.begin();
        final Long workerId = workerRepository.update(worker).getId();
        transaction.commit();

        //then
        Assertions.assertEquals(worker, getWorkerById(entityManager, workerId));
    }


    @Test
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
        Assertions.assertThrows(NoResultException.class, () -> getWorkerById(entityManager, workerId));
    }

    @Test
    public void shouldFindAllWorkersCarsAssignedWithoutLogField() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        WorkerRepository workerRepository = new WorkerRepository(entityManager);

        final List<Worker> expected = List.of(
                new Worker(1L, new Car(2L, 1500, 5, "PO6HH12", null), "12345678901", "Jakub", "Kąkolewski"),
                new Worker(2L, new Car(2L, 1500, 5, "PO6HH12", null), "12345678902", "Adam", "Nowak"),
                new Worker(3L, "12342678902", "Marian", "Nowak")
        );

        //when
        List<Worker> result = workerRepository.findAll();

        //then
        Assertions.assertAll(
                () -> {
                    for (int i = 0; i < result.size(); i++) {
                        compareWorker(expected.get(i), result.get(i));
                    }
                }
        );
    }

    @Test
    public void shouldFindFindByIdWithCarAssignedWithoutLogField() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        WorkerRepository workerRepository = new WorkerRepository(entityManager);

        long workerId = 1L;
        final Worker expected = new Worker(workerId, new Car(2L, 1500, 5, "PO6HH12", null), "12345678901", "Jakub", "Kąkolewski");

        //when
        Worker result = workerRepository.findById(workerId);

        //then
        compareWorker(expected, result);
    }


    private Worker getWorkerById(EntityManager entityManager, Long workerId) {
        return entityManager.createQuery("FROM Worker w WHERE w.id = :workerId", Worker.class).setParameter("workerId", workerId).getSingleResult();
    }

    private void compareWorker(Worker expectedWorker, Worker worker) {
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedWorker.getId(), worker.getId()),
                () -> Assertions.assertEquals(expectedWorker.getName(), worker.getName()),
                () -> Assertions.assertEquals(expectedWorker.getPesel(), worker.getPesel()),
                () -> Assertions.assertEquals(expectedWorker.getSurname(), worker.getSurname()),
                () -> Assertions.assertEquals(expectedWorker.getCar().getId(), worker.getCar().getId()),
                () -> Assertions.assertEquals(expectedWorker.getCar().getNumberOfSeats(), worker.getCar().getNumberOfSeats()),
                () -> Assertions.assertEquals(expectedWorker.getCar().getRegistrationNumber(), worker.getCar().getRegistrationNumber()),
                () -> Assertions.assertEquals(expectedWorker.getCar().getWeight(), worker.getCar().getWeight()),
                () -> Assertions.assertFalse(Hibernate.isInitialized(worker.getCar().getLog()))
        );
    }
}