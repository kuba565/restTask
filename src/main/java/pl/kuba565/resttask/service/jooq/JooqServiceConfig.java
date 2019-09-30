package pl.kuba565.resttask.service.jooq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.kuba565.resttask.repository.jooq.JooqCarRepositoryImpl;
import pl.kuba565.resttask.repository.jooq.JooqUserRepositoryImpl;
import pl.kuba565.resttask.repository.jooq.JooqWorkerRepositoryImpl;
import pl.kuba565.resttask.service.UserService;
import pl.kuba565.resttask.service.hibernate.CarServiceImpl;
import pl.kuba565.resttask.service.hibernate.WorkerServiceImpl;
import pl.kuba565.resttask.validator.CarValidator;
import pl.kuba565.resttask.validator.UserValidator;
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

    @Bean
    public UserService userService(JooqUserRepositoryImpl userRepository, UserValidator userValidator) {
        return new JooqUserServiceImpl(userRepository, userValidator);
    }
}
