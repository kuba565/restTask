package pl.kuba565.resttask.repository;

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
}
