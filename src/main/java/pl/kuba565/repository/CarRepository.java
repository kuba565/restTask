package pl.kuba565.repository;

import pl.kuba565.model.Car;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class CarRepository implements Repository<Car> {
    private final EntityManager entityManager;

    public CarRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Car update(Car car) {
        final Car newCar = new Car(car.getId(), car.getWeight(), car.getNumberOfSeats(), car.getRegistrationNumber());
        entityManager.merge(newCar);
        entityManager.flush();
        return newCar;
    }

    public Long create(Car car) {
        final Car newCar = new Car(car.getWeight(), car.getNumberOfSeats(), car.getRegistrationNumber(), car.getLog());
        entityManager.persist(newCar);
        entityManager.flush();
        return newCar.getId();
    }

    public List<Car> findAll() {
        final TypedQuery<Car> carTypedQuery = entityManager.createNamedQuery("Car.findAll", Car.class);
        return carTypedQuery.getResultList();
    }

    public void deleteById(Long id) {
        entityManager.createNamedQuery("Car.deleteById").setParameter("id", id).executeUpdate();
    }

    public Boolean checkIfExists(Long id) {
        return entityManager.createNamedQuery("Car.checkIfExists", Long.class).setParameter("id", id).getSingleResult() > 0;
    }

    public Long countRelationships(Long id) {
        return entityManager.createNamedQuery("Worker.countAssignedWorkers", Long.class).setParameter("id", id).getSingleResult();
    }

    public Car findById(Long id) {
        return entityManager.createNamedQuery("Car.findById", Car.class).setParameter("id", id).getSingleResult();
    }
}
