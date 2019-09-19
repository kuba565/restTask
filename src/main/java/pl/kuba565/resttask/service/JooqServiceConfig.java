package pl.kuba565.resttask.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.kuba565.resttask.repository.JooqCarRepositoryImpl;
import pl.kuba565.resttask.repository.JooqWorkerRepositoryImpl;
import pl.kuba565.resttask.validator.CarValidator;
import pl.kuba565.resttask.validator.WorkerValidator;

@Configuration
@Profile("jooq")
public class JooqServiceConfig {

    @Bean
    public CarServiceImpl carService(JooqCarRepositoryImpl carRepositoryImpl, CarValidator carValidator) {
        return new CarServiceImpl(carRepositoryImpl, carValidator);
    }

    @Bean
    public WorkerServiceImpl workerService(JooqWorkerRepositoryImpl workerRepositoryImpl, WorkerValidator workerValidator) {
        return new WorkerServiceImpl(workerRepositoryImpl, workerValidator);
    }
}
