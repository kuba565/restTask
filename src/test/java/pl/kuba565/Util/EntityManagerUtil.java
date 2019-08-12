package pl.kuba565.Util;

import pl.kuba565.model.Car;
import pl.kuba565.model.Worker;

import javax.persistence.EntityManager;

public class EntityManagerUtil {

    public static Car getCarById(EntityManager entityManager, Long carId) {
        return entityManager.createQuery("FROM Car c WHERE c.id = :carId", Car.class).setParameter("carId", carId).getSingleResult();
    }

    public static Worker getWorkerById(EntityManager entityManager, Long workerId) {
        return entityManager
                .createQuery("SELECT new Worker(w.id, w.pesel, w.name, w.surname) FROM Worker w WHERE w.id = :workerId", Worker.class)
                .setParameter("workerId", workerId)
                .getSingleResult();
    }
}
