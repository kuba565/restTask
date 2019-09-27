package pl.kuba565.resttask.repository;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kuba565.resttask.repository.hibernate.CarRepositoryImpl;
import pl.kuba565.resttask.repository.hibernate.UserRepositoryImpl;
import pl.kuba565.resttask.repository.hibernate.WorkerRepositoryImpl;
import pl.kuba565.resttask.repository.jooq.JooqCarRepositoryImpl;
import pl.kuba565.resttask.repository.jooq.JooqUserRepositoryImpl;
import pl.kuba565.resttask.repository.jooq.JooqWorkerRepositoryImpl;

import javax.persistence.EntityManager;

@Configuration
public class RepositoryConfig {
    @Bean
    public CarRepositoryImpl carRepositoryImpl(EntityManager entityManager) {
        return new CarRepositoryImpl(entityManager);
    }

    @Bean
    public WorkerRepositoryImpl workerRepositoryImpl(EntityManager entityManager) {
        return new WorkerRepositoryImpl(entityManager);
    }

    @Bean
    public UserRepositoryImpl userRepository(EntityManager entityManager) {
        return new UserRepositoryImpl(entityManager);
    }

    @Bean
    public JooqCarRepositoryImpl jooqCarRepositoryImpl(DSLContext dslContext) {
        return new JooqCarRepositoryImpl(dslContext);
    }

    @Bean
    public JooqWorkerRepositoryImpl jooqWorkerRepositoryImpl(DSLContext dslContext) {
        return new JooqWorkerRepositoryImpl(dslContext);
    }

    @Bean
    public JooqUserRepositoryImpl jooqUserRepository(DSLContext dslContext) {
        return new JooqUserRepositoryImpl(dslContext);
    }
}
