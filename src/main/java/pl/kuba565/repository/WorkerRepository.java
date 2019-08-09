package pl.kuba565.repository;

import pl.kuba565.model.Worker;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkerRepository implements Repository<Worker> {
    private final EntityManager entityManager;

    public WorkerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Worker update(Worker worker) {
        final Worker newWorker = new Worker(worker.getId(), worker.getCar(), worker.getPesel(), worker.getName(), worker.getSurname());
        entityManager.merge(newWorker);
        return newWorker;
    }

    public Long create(Worker worker) {
        final Worker newWorker = new Worker(worker.getCar(), worker.getPesel(), worker.getName(), worker.getSurname());
        entityManager.persist(newWorker);
        return newWorker.getId();
    }

    //TODO: konstruktor workera z polami car, lub osobna tabela z log, lub criteriabuilder bez pola z car
    // ad. 1 - brzydkie rozwiązanie
    // ad. 2 - chyba najlepiej
    // ad 3 - musze stworzyć model obiektu bez pola - brzydkie rozwiązanie
    public List<Worker> findAll() {
        return entityManager
                .createNamedQuery("Worker.findAll", Worker.class)
                .getResultList();
    }

    public void deleteById(Long id) {
        entityManager
                .createNamedQuery("Worker.deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Boolean checkIfExists(Long id) {
        return entityManager
                .createNamedQuery("Worker.checkIfExists", Long.class)
                .setParameter("id", id)
                .getSingleResult() > 0;
    }

    public Worker findById(Long id) {
//        return entityManager
//                .createNamedQuery("Worker.findById", Worker.class)
//                .setParameter("id", id)
//                .getSingleResult();

        EntityGraph graph = this.entityManager.getEntityGraph("workerCarGraph");

        Map hints = new HashMap();
        hints.put("javax.persistence.loadgraph", graph);

        return this.entityManager.find(Worker.class, id, hints);
    }

    public Long countRelationships(Long id) {
        return null;
    }
}
