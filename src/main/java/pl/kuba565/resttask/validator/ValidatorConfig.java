package pl.kuba565.resttask.validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kuba565.resttask.repository.CarRepositoryImpl;
import pl.kuba565.resttask.repository.WorkerRepositoryImpl;

@Configuration
public class ValidatorConfig {
    @Bean
    public CarValidator carValidator(CarRepositoryImpl carRepositoryImpl) {
        return new CarValidator(carRepositoryImpl);
    }

    @Bean
    public WorkerValidator workerValidator(WorkerRepositoryImpl workerRepositoryImpl) {
        return new WorkerValidator(workerRepositoryImpl);
    }
}
