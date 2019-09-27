package pl.kuba565.resttask.service.hibernate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.kuba565.resttask.repository.hibernate.CarRepositoryImpl;
import pl.kuba565.resttask.repository.hibernate.UserRepositoryImpl;
import pl.kuba565.resttask.repository.hibernate.WorkerRepositoryImpl;
import pl.kuba565.resttask.validator.CarValidator;
import pl.kuba565.resttask.validator.UserValidator;
import pl.kuba565.resttask.validator.WorkerValidator;

@Configuration
@Profile("!jooq")
public class ServiceConfig {
    @Bean
    public CarServiceImpl carService(CarRepositoryImpl carRepositoryImpl, CarValidator carValidator) {
        return new CarServiceImpl(carRepositoryImpl, carValidator);
    }

    @Bean
    public WorkerServiceImpl workerService(WorkerRepositoryImpl workerRepositoryImpl, WorkerValidator workerValidator) {
        return new WorkerServiceImpl(workerRepositoryImpl, workerValidator);
    }

    @Bean
    public UserServiceImpl userService(UserRepositoryImpl userRepository, UserValidator userValidator) {
        return new UserServiceImpl(userRepository, userValidator);
    }
}
