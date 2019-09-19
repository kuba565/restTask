package pl.kuba565.resttask.repository;

import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public JooqCarRepositoryImpl jooqCarRepositoryImpl(DSLContext dslContext) {
        return new JooqCarRepositoryImpl(dslContext);
    }

    @Bean
    public JooqWorkerRepositoryImpl jooqWorkerRepositoryImpl(DSLContext dslContext) {
        return new JooqWorkerRepositoryImpl(dslContext);
    }
}
