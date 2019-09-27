package pl.kuba565.resttask.repository.jooq;

import pl.kuba565.resttask.model.BaseModel;
import pl.kuba565.resttask.repository.GenericRepository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class JooqGenericRepositoryImpl<T extends BaseModel> implements GenericRepository<T> {
    private final Class aClass;

    public JooqGenericRepositoryImpl() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        aClass = (Class) parameterizedType.getActualTypeArguments()[0];
    }
}
