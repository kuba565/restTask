package pl.kuba565.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kuba565.service.CarService;
import pl.kuba565.service.WorkerService;

@Configuration
public class ControllerConfig {
    @Bean
    public CarController carController(CarService carService) {
        return new CarController(carService);
    }

    @Bean
    public WorkerController workerController(WorkerService workerService) {
        return new WorkerController(workerService);
    }
}
