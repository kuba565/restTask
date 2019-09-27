package pl.kuba565.resttask.validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kuba565.resttask.repository.hibernate.CarRepositoryImpl;
import pl.kuba565.resttask.repository.hibernate.UserRepositoryImpl;
import pl.kuba565.resttask.repository.hibernate.WorkerRepositoryImpl;

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

    @Bean
    public UserValidator userValidator(UserRepositoryImpl userRepository) {
        return new UserValidator(userRepository);
    }
}
