package pl.kuba565.resttask.graphql;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kuba565.resttask.repository.CarRepositoryImpl;
import pl.kuba565.resttask.repository.WorkerRepositoryImpl;

@Configuration
public class GraphqlConfig {
    @Bean
    public Query query(CarRepositoryImpl carRepository, WorkerRepositoryImpl workerRepository) {
        return new Query(carRepository, workerRepository);
    }
}
