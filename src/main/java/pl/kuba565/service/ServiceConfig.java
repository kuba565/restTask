package pl.kuba565.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kuba565.repository.CarRepository;
import pl.kuba565.repository.WorkerRepository;
import pl.kuba565.validator.CarValidator;
import pl.kuba565.validator.WorkerValidator;

@Configuration
public class ServiceConfig {
    @Bean
    public CarService carService(CarValidator carValidator, CarRepository carRepository) {
        return new CarService(carRepository, carValidator);
    }

    @Bean
    public WorkerService workerService(WorkerValidator workerValidator, WorkerRepository workerRepository) {
        return new WorkerService(workerRepository, workerValidator);
    }
}
