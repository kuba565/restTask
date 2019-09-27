package pl.kuba565.resttask.repository.hibernate;

import pl.kuba565.resttask.model.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl extends GenericRepositoryImpl<User> {
    private EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findAll() {
        return entityManager.createNamedQuery("User.findAll", User.class)
                .getResultList();
    }

    @Override
    public User findById(Long id) {
        return entityManager.createNamedQuery("User.findById", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public User findByName(String name) {
        return entityManager.createNamedQuery("User.findByName", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
