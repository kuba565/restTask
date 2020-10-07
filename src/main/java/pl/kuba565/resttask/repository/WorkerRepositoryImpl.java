package pl.kuba565.resttask.repository;

import pl.kuba565.resttask.model.Worker;

import javax.persistence.EntityManager;
import java.util.List;

public class WorkerRepositoryImpl extends GenericRepositoryImpl<Worker> {
    private EntityManager entityManager;

    public WorkerRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public List<Worker> findAll() {
        return entityManager.createNamedQuery("Worker.findAll", Worker.class)
                .getResultList();
    }

    @Override
    public Worker findById(Long id) {
        return entityManager.createNamedQuery("Worker.findById", Worker.class)
                .setParameter("id", id)
                .getSingleResult();

    }
}
