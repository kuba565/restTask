package pl.kuba565.resttask.transformer.model;

import org.hibernate.Hibernate;
import pl.kuba565.resttask.dto.WorkerDto;
import pl.kuba565.resttask.model.Worker;

import java.util.function.Function;

public class WorkerTransformerImpl extends GenericModelTransformerImpl<Worker, WorkerDto> implements Function<Worker, WorkerDto> {
    private CarTransformerImpl carTransformerImpl;

    public WorkerTransformerImpl(CarTransformerImpl carTransformerImpl) {
        this.carTransformerImpl = carTransformerImpl;
    }

    @Override
    public WorkerDto apply(Worker worker) {
        WorkerDto.WorkerDtoBuilder workerDtoBuilder = WorkerDto.builder()
                .id(worker.getId())
                .name(worker.getName())
                .pesel(worker.getPesel())
                .surname(worker.getSurname());
        if (Hibernate.isInitialized(worker.getCar()) && worker.getCar() != null) {
            workerDtoBuilder.carDto(carTransformerImpl.apply(worker.getCar()));
        }
        return workerDtoBuilder.build();
    }
}
