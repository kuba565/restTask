package pl.kuba565.controller;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.TestBed;
import pl.kuba565.model.Car;
import pl.kuba565.model.Worker;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;


public class WorkerControllerTest extends TestBed {
    @Autowired
    private WorkerController workerController;
    @Autowired
    private EntityManager entityManager;

    private final List<Worker> expectedWorkers =
            List.of(new Worker(1L, new Car(2L, 1500, 5, "PO6HH12", "test"),
                            "12345678901", "Jakub", "Kąkolewski"),
                    new Worker(2L, new Car(2L, 1500, 5, "PO6HH12", "test"),
                            "12345678902", "Adam", "Nowak"),
                    new Worker(3L, "12342678902", "Marian", "Nowak"));

    private final Worker expectedWorker = new Worker(1L,
            new Car(2L, 1500, 5, "PO6HH12", "test"),
            "12345678901", "Jakub", "Kąkolewski");

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(workerController);
    }

    @Test
    public void shouldFindById() {
        //when
        Worker worker = workerController.findById(1L);

        //then
        Assertions.assertEquals(expectedWorker, worker);
    }

    @Test
    public void shouldFindAll() {
        //when
        List<Worker> workers = workerController.findAll();

        //then
        Assertions.assertEquals(expectedWorkers, workers);
    }

    @Test
    public void shouldPut() {
        //given
        final Long workerId = 1L;
        final Worker expectedWorker = new Worker(workerId,  "22222", "AAAA", "BBBB");

        //when
        workerController.put(expectedWorker);

        //then
        Assertions.assertEquals(expectedWorker, entityManager.createQuery("SELECT new Worker(w.id, w.pesel, w.name, w.surname) FROM Worker w WHERE w.id = :workerId")
                .setParameter("workerId", workerId).getSingleResult());
    }

    @Test
    public void shouldPost() {
        //given
        final Worker expectedWorker = new Worker("22222", "AAAA", "BBBB");

        //when
        Long workerId = workerController.post(expectedWorker);

        //then
        Assertions.assertEquals(expectedWorker, entityManager.createQuery("SELECT new Worker(w.pesel, w.name, w.surname) FROM Worker w WHERE w.id = :workerId")
                .setParameter("workerId", workerId).getSingleResult());
    }

    @Test
    public void shouldDeleteById() {
        //when
        final Long workerId = 1L;
        workerController.deleteById(workerId);

        //then
        Assertions.assertThrows(NoResultException.class, () -> entityManager.createQuery("SELECT w FROM Worker w WHERE w.id = :workerId")
                .setParameter("workerId", workerId).getSingleResult());
    }
}