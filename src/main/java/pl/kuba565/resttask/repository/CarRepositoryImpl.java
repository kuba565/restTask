package pl.kuba565.resttask.repository;

import pl.kuba565.resttask.model.Car;

import javax.persistence.EntityManager;
import java.util.List;

public class CarRepositoryImpl extends GenericRepositoryImpl<Car> {
    private EntityManager entityManager;

    public CarRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    public Long countAssignedWorkers(Long id) {
        return entityManager
                .createNamedQuery("Worker.countAssignedWorkers", Long.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Car findById(Long id) {
        return entityManager.createNamedQuery("Car.findById", Car.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Car> findAll() {
        return entityManager.createNamedQuery("Car.findAll", Car.class)
                .getResultList();
    }
}
