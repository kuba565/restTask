package pl.kuba565.resttask.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import pl.kuba565.resttask.dto.CarDto;
import pl.kuba565.resttask.dto.WorkerDto;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.model.Worker;
import pl.kuba565.resttask.service.GenericService;
import pl.kuba565.resttask.transformer.model.CarTransformerImpl;
import pl.kuba565.resttask.transformer.model.WorkerTransformerImpl;

import java.util.List;
import java.util.stream.Collectors;

public class Query implements GraphQLQueryResolver {
    private GenericService<Car> carGenericService;
    private GenericService<Worker> workerGenericService;
    private CarTransformerImpl carTransformer;
    private WorkerTransformerImpl workerTransformer;

    public Query(GenericService<Car> carGenericService,
                 GenericService<Worker> workerGenericService,
                 CarTransformerImpl carTransformer,
                 WorkerTransformerImpl workerTransformer) {
        this.workerGenericService = workerGenericService;
        this.carGenericService = carGenericService;
        this.carTransformer = carTransformer;
        this.workerTransformer = workerTransformer;
    }

    public List<CarDto> getCarDtos() {
        return carGenericService.findAll()
                .stream()
                .map(car -> carTransformer.apply(car))
                .collect(Collectors.toList());
    }

    public CarDto getCarDto(Long id) {
        return carTransformer.apply(carGenericService.findById(id));
    }

    public List<WorkerDto> getWorkerDtos() {
        return workerGenericService.findAll()
                .stream()
                .map(worker -> workerTransformer.apply(worker))
                .collect(Collectors.toList());
    }

    public WorkerDto getWorkerDto(Long id) {
        return workerTransformer.apply(
                workerGenericService.findById(id)
        );
    }
}