package pl.kuba565.resttask.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kuba565.resttask.repository.CarRepositoryImpl;
import pl.kuba565.resttask.repository.WorkerRepositoryImpl;
import pl.kuba565.resttask.validator.CarValidator;
import pl.kuba565.resttask.validator.WorkerValidator;

@Configuration
public class ServiceConfig {
    @Bean
    public CarServiceImpl carService(CarRepositoryImpl carRepositoryImpl, CarValidator carValidator) {
        return new CarServiceImpl(carRepositoryImpl, carValidator);
    }

    @Bean
    public WorkerServiceImpl workerService(WorkerRepositoryImpl workerRepositoryImpl, WorkerValidator workerValidator) {
        return new WorkerServiceImpl(workerRepositoryImpl, workerValidator);
    }
}
