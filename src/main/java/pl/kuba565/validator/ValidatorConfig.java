package pl.kuba565.validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kuba565.repository.CarRepository;
import pl.kuba565.repository.WorkerRepository;

@Configuration
public class ValidatorConfig {
    @Bean
    public CarValidator carValidator(CarRepository carRepository) {
        return new CarValidator(carRepository);
    }

    @Bean
    public WorkerValidator workerValidator(WorkerRepository workerRepository) {
        return new WorkerValidator(workerRepository);
    }
}
