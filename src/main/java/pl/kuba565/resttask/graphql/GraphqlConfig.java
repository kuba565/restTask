package pl.kuba565.resttask.graphql;

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
public class GraphqlConfig {
    @Bean
    public Query query(GenericService<Car> carGenericService,
                       GenericService<Worker> workerGenericService,
                       CarTransformerImpl carTransformer,
                       WorkerTransformerImpl workerTransformer) {
        return new Query(carGenericService, workerGenericService, carTransformer, workerTransformer);
    }

    @Bean
    public Mutation mutation(GenericService<Worker> workerGenericService,
                             WorkerDtoTransformerImpl workerDtoTransformer,
                             GenericService<Car> carGenericService,
                             CarDtoTransformerImpl carDtoTransformer) {
        return new Mutation(workerGenericService, workerDtoTransformer, carGenericService, carDtoTransformer);
    }
}
