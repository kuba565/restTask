package pl.kuba565.resttask.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import pl.kuba565.resttask.dto.CarDto;
import pl.kuba565.resttask.dto.WorkerDto;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.model.Worker;
import pl.kuba565.resttask.service.GenericService;
import pl.kuba565.resttask.transformer.dto.CarDtoTransformerImpl;
import pl.kuba565.resttask.transformer.dto.WorkerDtoTransformerImpl;

public class Mutation implements GraphQLMutationResolver {
    private GenericService<Worker> workerGenericService;
    private GenericService<Car> carGenericService;
    private WorkerDtoTransformerImpl workerDtoTransformer;
    private CarDtoTransformerImpl carDtoTransformer;

    public Mutation(GenericService<Worker> workerGenericService,
                    WorkerDtoTransformerImpl workerDtoTransformer,
                    GenericService<Car> carGenericService,
                    CarDtoTransformerImpl carDtoTransformer) {
        this.workerDtoTransformer = workerDtoTransformer;
        this.workerGenericService = workerGenericService;
        this.carGenericService = carGenericService;
        this.carDtoTransformer = carDtoTransformer;
    }

    public Boolean writeCarDto(CarDto carDto) {
        carGenericService.create(carDtoTransformer.apply(carDto));
        return true;
    }

    public Boolean writeWorkerDto(WorkerDto workerDto) {
        workerGenericService.create(workerDtoTransformer.apply(workerDto));
        return true;
    }

    public Boolean updateCarDto(CarDto carDto) {
        carGenericService.update(carDtoTransformer.apply(carDto));
        return true;
    }

    public Boolean updateWorkerDto(WorkerDto workerDto) {
        workerGenericService.update(workerDtoTransformer.apply(workerDto));
        return true;
    }

    public Boolean deleteCarDto(Long id) {
        carGenericService.deleteById(id);
        return true;
    }

    public Boolean deleteWorkerDto(Long id) {
        workerGenericService.deleteById(id);
        return true;
    }
}