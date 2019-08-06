package pl.kuba565.repository;

import pl.kuba565.model.Worker;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class WorkerRepository implements Repository<Worker> {
    private final EntityManager entityManager;

    public WorkerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Worker update(Worker worker) {
        final Worker newWorker = new Worker(worker.getId(), worker.getCar(), worker.getPesel(), worker.getName(), worker.getSurname());
        entityManager.merge(newWorker);
        entityManager.flush();
        return newWorker;
    }

    public Long create(Worker worker) {
        final Worker newWorker = new Worker(worker.getCar(), worker.getPesel(), worker.getName(), worker.getSurname());
        entityManager.persist(newWorker);
        entityManager.flush();
        return newWorker.getId();
    }

    public List<Worker> findAll() {
        final TypedQuery<Worker> carTypedQuery = entityManager.createNamedQuery("Worker.findAll", Worker.class);
        return carTypedQuery.getResultList();
    }

    public void deleteById(Long id) {
        entityManager.createNamedQuery("Worker.deleteById").setParameter("id", id).executeUpdate();
    }

    public Boolean checkIfExists(Long id) {
        return entityManager.createNamedQuery("Worker.checkIfExists", Long.class).setParameter("id", id).getSingleResult() > 0;
    }

    public Worker findById(Long id) {
        return entityManager.createNamedQuery("Worker.findById", Worker.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public Long countAssignedWorkers(Long id) {
        return null;
    }
}
