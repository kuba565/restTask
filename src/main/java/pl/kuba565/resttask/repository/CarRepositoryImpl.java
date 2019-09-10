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
//        EntityGraph entityGraph = entityManager.getEntityGraph("find-car");
//        return entityManager.createNamedQuery("Car.findById", Car.class)
//                .setParameter("id", id)
//                .setHint("javax.persistence.fetchgraph", entityGraph)
//                .getSingleResult();
        return entityManager.createNamedQuery("Car.findById", Car.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Car> findAll() {
//        EntityGraph entityGraph = entityManager.getEntityGraph("find-car");
//        return entityManager.createNamedQuery("Car.findAll", Car.class)
//                .setHint("javax.persistence.fetchgraph", entityGraph)
//                .getResultList();
        return entityManager.createNamedQuery("Car.findAll", Car.class)
                .getResultList();
    }
}
