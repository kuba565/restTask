package pl.kuba565.service;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.TestBed;
import pl.kuba565.exception.ValidationException;
import pl.kuba565.model.Car;
import pl.kuba565.model.Worker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;

public class WorkerServiceTest extends TestBed {
    @Autowired
    private WorkerService workerService;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void shouldFindAllWorkersWithoutCarLogField() {
        //given
        List<Worker> expected = List.of(
                new Worker(1L, new Car(2L, 1500, 5, "PO6HH12"), "12345678901", "Jakub", "Kąkolewski"),
                new Worker(2L, new Car(2L, 1500, 5, "PO6HH12"), "12345678902", "Adam", "Nowak"),
                new Worker(3L, "12342678902", "Marian", "Nowak")
        );

        //when
        List<Worker> result = workerService.findAll();

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
    public void shouldFindFindByIdWithoutCarLogField() {
        long id = 1L;
        Worker expected = new Worker(id, new Car(2L, 1500, 5, "PO6HH12"), "12345678901", "Jakub", "Kąkolewski");

        //when
        Worker result = workerService.findById(id);

        //then
        compareWorker(expected, result);
    }

    @Test
    public void shouldCreateWorker() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        final Worker worker = new Worker("1", "g", "a");

        //when
        Long workerId = workerService.create(worker);

        //then
        worker.setId(workerId);
        Assertions.assertEquals(worker, getWorkerById(entityManager, workerId));
    }

    @Test
    public void shouldUpdateWorker() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        final Worker worker = new Worker(1L, "1", "g", "a");

        //when
        worker.setName("John");
        Long workerID = workerService.update(worker).getId();

        //then
        Assertions.assertEquals(worker, getWorkerById(entityManager, workerID));
    }

    @Test
    public void shouldDeleteWorker() {
        //given
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        final Long workerId = 1L;

        //when
        transaction.begin();
        workerService.deleteById(workerId);
        transaction.commit();

        //then
        Assertions.assertThrows(NoResultException.class,
                () -> getWorkerById(entityManager, workerId)
        );
    }

    @Test
    public void shouldThrowValidationExceptionOnDeleteWorkerAssignedToCar() {
        //given
        final Long workerId = 9999L;

        //then
        Assertions.assertThrows(ValidationException.class, () -> workerService.deleteById(workerId));
    }

    private Worker getWorkerById(EntityManager entityManager, Long workerID) {
        return entityManager.createQuery("Select w From Worker w where w.id = :workerId", Worker.class).setParameter("workerId", workerID).getSingleResult();
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