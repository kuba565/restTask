package pl.kuba565.resttask.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.model.Worker;
import pl.kuba565.resttask.service.GenericService;
import pl.kuba565.resttask.transformer.dto.CarDtoTransformerImpl;
import pl.kuba565.resttask.transformer.dto.WorkerDtoTransformerImpl;
import pl.kuba565.resttask.transformer.model.CarTransformerImpl;
import pl.kuba565.resttask.transformer.model.WorkerTransformerImpl;

@Configuration
public class ControllerConfig {
    @Bean
    public CarControllerImpl carControllerImpl(GenericService<Car> carGenericService,
                                               CarDtoTransformerImpl carDtoTransformer,
                                               CarTransformerImpl carTransformer) {
        return new CarControllerImpl(carGenericService, carDtoTransformer, carTransformer);
    }

    @Bean
    public WorkerControllerImpl workerControllerImpl(GenericService<Worker> workerGenericService,
                                                     WorkerDtoTransformerImpl workerDtoTransformer,
                                                     WorkerTransformerImpl workerTransformer) {
        return new WorkerControllerImpl(workerGenericService, workerDtoTransformer, workerTransformer);
    }
}
