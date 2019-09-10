package pl.kuba565.resttask.repository;

import pl.kuba565.resttask.model.BaseModel;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class GenericRepositoryImpl<T extends BaseModel> implements GenericRepository<T> {
    private final EntityManager entityManager;
    private final Class aClass;

    public GenericRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        aClass = (Class) parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void create(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(aClass, id));
    }

    @Override
    public boolean exists(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(aClass);
        Root<T> root = query.from(aClass);
        query.where(criteriaBuilder.equal(root.get("id"), id));

        return !entityManager.createQuery(query).getResultList().isEmpty();
    }
}
