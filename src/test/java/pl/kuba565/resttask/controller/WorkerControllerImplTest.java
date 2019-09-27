package pl.kuba565.resttask.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.AbstractTest;
import pl.kuba565.resttask.dto.CarDto;
import pl.kuba565.resttask.dto.LogDto;
import pl.kuba565.resttask.dto.WorkerDto;
import pl.kuba565.resttask.exception.ValidationException;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.model.Log;
import pl.kuba565.resttask.model.Worker;
import pl.kuba565.resttask.service.hibernate.WorkerServiceImpl;
import pl.kuba565.resttask.transformer.model.WorkerTransformerImpl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static pl.kuba565.util.WorkerDtoAssertionUtil.assertWorkerDtoFetching;
import static pl.kuba565.util.WorkerDtoAssertionUtil.assertWorkerDtosFetching;

public class WorkerControllerImplTest extends AbstractTest {
    @Autowired
    private WorkerController workerController;

    @Autowired
    private WorkerServiceImpl workerServiceImpl;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private WorkerTransformerImpl workerToWorkerDtoTransformerImpl;

    @Autowired
    private WorkerControllerImpl workerRepositoryImpl;

    private WorkerDto exampleWorkerDto;

    private List<WorkerDto> exampleWorkerDtos;

    private Long workerId;

    @BeforeEach
    public void init() {
        exampleWorkerDtos = new ArrayList<>();
        List<Worker> exampleWorkers = workerServiceImpl.findAll();
        if (exampleWorkers.isEmpty()) {
            workerServiceImpl.create(new Worker(new Car(new Log("test")), "aa", "aaac", "dasdsad"));
            exampleWorkers = workerServiceImpl.findAll();
            exampleWorkers.forEach(worker -> exampleWorkerDtos.add(workerToWorkerDtoTransformerImpl.apply(worker)));
            Worker exampleWorker = exampleWorkers.get(0);
            exampleWorkerDto = workerToWorkerDtoTransformerImpl.apply(exampleWorker);
            workerId = exampleWorkerDto.getId();
        } else {
            workerId = 1L;
            exampleWorkers.forEach(worker -> exampleWorkerDtos.add(workerToWorkerDtoTransformerImpl.apply(worker)));
            Worker exampleWorker = exampleWorkers.get(0);
            exampleWorkerDto = workerToWorkerDtoTransformerImpl.apply(exampleWorker);
        }
    }

    @Test
    public void contextLoads() {
        assertNotNull(workerController);
    }

    @Test
    public void shouldFindByIdWithoutCarLogField() {
        //when
        WorkerDto workerDto = workerController.findById(workerId);

        //then
        assertAll(() -> assertWorkerDtoFetching(exampleWorkerDto, workerDto));
    }

    @Test
    public void shouldFindAllWithoutCarLogField() {
        //when
        List<WorkerDto> workerDtos = workerController.findAll();

        //then
        assertAll(() -> assertWorkerDtosFetching(exampleWorkerDtos, workerDtos));
    }

    @Test
    public void shouldUpdate() {
        //given
        final WorkerDto expectedWorkerDto = new WorkerDto(workerId, new CarDto(1, 2, "aaa", new LogDto("aaa")), "AAAA", "BBBB", "ADSADAS");

        //when
        workerController.update(expectedWorkerDto);

        //then
        final Long id = entityManager
                .createQuery("SELECT w.id FROM Worker w WHERE w.surname = :surname", Long.class)
                .setParameter("surname", expectedWorkerDto.getSurname())
                .getSingleResult();
        assertWorkerDtoFetching(expectedWorkerDto, workerRepositoryImpl.findById(id));
    }


    @Test
    public void shouldCreate() {
        //given
        final WorkerDto newWorkerDto = new WorkerDto(new CarDto(new LogDto("test")), "22222", "AAAA", "BBBB");

        //when
        workerController.create(newWorkerDto);

        //then
        final Long id = entityManager
                .createQuery("SELECT w.id FROM Worker w WHERE w.surname = :surname", Long.class)
                .setParameter("surname", newWorkerDto.getSurname())
                .getSingleResult();
        assertWorkerDtoFetching(newWorkerDto, workerRepositoryImpl.findById(id));
    }

    @Test
    public void shouldDeleteById() {
        //when
        workerController.deleteById(workerId);

        //then
        assertThrows(NoResultException.class, () -> workerRepositoryImpl.findById(workerId));
    }

    @Test
    public void shouldValidateNonExistentIdOnDelete() {
        //then
        assertThrows(ValidationException.class, () -> workerController.deleteById(-1L));
    }

    @Test
    public void shouldNotCreateUnvalidated() {
        //given
        final WorkerDto expectedWorker = new WorkerDto(1L, "wwww", "aaa", "wwww");

        //then
        assertThrows(ValidationException.class, () -> workerController.create(expectedWorker));
    }
}