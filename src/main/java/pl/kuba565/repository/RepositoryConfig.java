package pl.kuba565.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class RepositoryConfig {
    @Bean
    CarRepository carRepository(EntityManager entityManager) {
        return new CarRepository(entityManager);
    }

    @Bean
    WorkerRepository workerRepository(EntityManager entityManager) {
        return new WorkerRepository(entityManager);
    }
}
