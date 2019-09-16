package pl.kuba565.resttask.repository;

import org.jooq.DSLContext;
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
import static pl.kuba565.util.WorkerAssertionUtil.*;

class JooqWorkerRepositoryImplTest extends AbstractTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DSLContext dslContext;

    @Autowired
    private JooqWorkerRepositoryImpl workerRepository;

    @Autowired
    private WorkerServiceImpl workerService;

    private Worker exampleWorker;

    private List<Worker> exampleWorkers;

    private Long workerId;

    @BeforeEach
    public void init() {
        exampleWorkers = workerService.findAll();
        if (exampleWorkers.isEmpty()) {
            workerService.create(new Worker(new Car(new Log("test"), 1, 1, "aa"), "1337", "John", "Connor"));
            exampleWorkers = workerService.findAll();
        }
        exampleWorker = exampleWorkers.get(0);
        workerId = exampleWorker.getId();
    }

    @Test
    @Transactional
    public void shouldCreateWorker() {
        //given
        final Worker worker = new Worker(new Car(new Log(""), 1, 1, ""), "12345678901", "John", "Hancock");

        //when
        workerRepository.create(worker);

        //then
        workerId = entityManager
                .createQuery("SELECT w.id FROM Worker w WHERE w.surname = :surname", Long.class)
                .setParameter("surname", worker.getSurname())
                .getSingleResult();

        assertWorkerFetchingWithoutLog(worker, entityManager
                .createQuery("FROM Worker w WHERE w.id = :id", Worker.class)
                .setParameter("id", workerId)
                .getSingleResult()
        );
    }

    @Test
    @Transactional
    public void shouldUpdateWorker() {
        //given
        final Worker worker = new Worker(workerId, "12345678901", "aa", "TEST1");

        //when
        workerRepository.update(worker);

        //then
        workerId = entityManager
                .createQuery("SELECT w.id FROM Worker w WHERE w.surname = :surname", Long.class)
                .setParameter("surname", worker.getSurname())
                .getSingleResult();
        assertWorkerFetching(worker, workerService.findById(workerId));
    }

    @Test
    @Transactional
    public void shouldDeleteWorker() {
        //when
        workerRepository.deleteById(workerId);

        //then
        assertThrows(NoResultException.class, () -> workerRepository.findById(workerId));
    }

    @Test
    @Transactional
    public void shouldFindAllWorkersCarsAssignedWithoutLogField() {
        //when
        List<Worker> result = workerRepository.findAll();

        //then
        assertAll(() -> assertWorkersFetching(exampleWorkers, result));
    }

    @Test
    @Transactional
    public void shouldFindFindByIdWithCarAssignedWithoutLogField() {
        //given
        final long id = exampleWorker.getId();

        //when
        Worker result = workerRepository.findById(id);

        //then
        assertAll(() -> assertWorkerFetching(exampleWorker, result));
    }
}