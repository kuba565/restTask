package pl.kuba565.repository;

import pl.kuba565.model.Car;

import javax.persistence.EntityManager;
import java.util.List;

public class CarRepository implements Repository<Car> {
    private final EntityManager entityManager;

    public CarRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Car update(Car newCar) {
        entityManager.merge(newCar);
        return newCar;
    }

    public Long create(Car car) {
        entityManager.persist(car);
        return car.getId();
    }

    public List<Car> findAll() {
        return entityManager
                .createNamedQuery("Car.findAll", Car.class)
                .getResultList();
    }

    public void deleteById(Long id) {
        entityManager
                .createNamedQuery("Car.deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Boolean checkIfExists(Long id) {
        return entityManager
                .createNamedQuery("Car.checkIfExists", Long.class)
                .setParameter("id", id)
                .getSingleResult() > 0;
    }

    public Long countRelationships(Long id) {
        return entityManager
                .createNamedQuery("Worker.countAssignedWorkers", Long.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Car findById(Long id) {
        return entityManager
                .createNamedQuery("Car.findById", Car.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
