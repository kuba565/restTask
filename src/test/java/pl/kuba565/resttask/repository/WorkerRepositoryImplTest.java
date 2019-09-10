package pl.kuba565.resttask.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.AbstractTest;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.model.Log;
import pl.kuba565.resttask.model.Worker;
import pl.kuba565.resttask.service.WorkerServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pl.kuba565.util.WorkerAssertionUtil.assertWorkerFetching;
import static pl.kuba565.util.WorkerAssertionUtil.assertWorkersFetching;

public class WorkerRepositoryImplTest extends AbstractTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private WorkerRepositoryImpl workerRepositoryImpl;

    @Autowired
    private WorkerServiceImpl workerServiceImpl;

    private Worker exampleWorker;

    private List<Worker> exampleWorkers;

    private Long workerId;

    @BeforeEach
    public void init() {
        exampleWorkers = workerServiceImpl.findAll();
        if (exampleWorkers.isEmpty()) {
            workerServiceImpl.create(new Worker(new Car(new Log("test")), "1337", "John", "Connor"));
            exampleWorkers = workerServiceImpl.findAll();
        }
        exampleWorker = exampleWorkers.get(0);
        workerId = exampleWorker.getId();
    }

    @Test
    @Transactional
    public void shouldCreateWorker() {
        //given
        final Worker worker = new Worker(new Car(new Log("test")), "12345678901", "John", "Hancock");

        //when
        workerRepositoryImpl.create(worker);

        //then
        Long workerId = entityManager
                .createQuery("SELECT w.id FROM Worker w WHERE w.surname = :surname", Long.class)
                .setParameter("surname", worker.getSurname())
                .getSingleResult();
        assertWorkerFetching(worker, workerRepositoryImpl.findById(workerId));
    }

    @Test
    @Transactional
    public void shouldUpdateWorker() {
        //given
        final Worker worker = new Worker(workerId, "12345678901", "aa", "TEST1", (new Car(new Log("test"))));

        //when
        workerRepositoryImpl.update(worker);

        //then
        workerId = entityManager
                .createQuery("SELECT w.id FROM Worker w WHERE w.surname = :surname", Long.class)
                .setParameter("surname", worker.getSurname())
                .getSingleResult();
        assertWorkerFetching(worker, workerRepositoryImpl.findById(workerId));
    }

    @Test
    @Transactional
    public void shouldDeleteWorker() {
        //when
        workerRepositoryImpl.deleteById(workerId);

        //then
        assertThrows(NoResultException.class, () -> workerRepositoryImpl.findById(workerId));
    }

    @Test
    @Transactional
    public void shouldFindAllWorkersCarsAssignedWithoutLogField() {
        //when
        List<Worker> result = workerRepositoryImpl.findAll();
        entityManager.clear();

        //then
        assertAll(() -> assertWorkersFetching(exampleWorkers, result));
    }

    @Test
    @Transactional
    public void shouldFindFindByIdWithCarAssignedWithoutLogField() {
        //given
        final long id = exampleWorker.getId();

        //when
        Worker result = workerRepositoryImpl.findById(id);
        entityManager.clear();

        //then
        assertAll(() -> assertWorkerFetching(exampleWorker, result));
    }
}