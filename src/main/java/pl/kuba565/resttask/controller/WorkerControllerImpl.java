package pl.kuba565.resttask.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kuba565.resttask.dto.WorkerDto;
import pl.kuba565.resttask.model.Worker;
import pl.kuba565.resttask.service.GenericService;
import pl.kuba565.resttask.transformer.dto.WorkerDtoTransformerImpl;
import pl.kuba565.resttask.transformer.model.WorkerTransformerImpl;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static pl.kuba565.resttask.util.StringUtil.WORKER;

@RequestMapping(WORKER)
@Validated
public class WorkerControllerImpl extends GenericControllerImpl<Worker, WorkerDto> implements WorkerController {
    private final GenericService<Worker> workerGenericService;
    private WorkerDtoTransformerImpl workerDtoTransformer;
    private WorkerTransformerImpl workerTransformer;

    public WorkerControllerImpl(GenericService<Worker> workerGenericService,
                                WorkerDtoTransformerImpl workerDtoTransformer,
                                WorkerTransformerImpl workerTransformer) {
        super(workerGenericService, workerDtoTransformer, workerTransformer);
        this.workerDtoTransformer = workerDtoTransformer;
        this.workerTransformer = workerTransformer;
        this.workerGenericService = workerGenericService;
    }

    public WorkerDto findById(@PathVariable(value = "id") @NotNull Long id) {
        return workerTransformer.apply(
                workerGenericService.findById(id)
        );
    }

    public List<WorkerDto> findAll() {
        return workerGenericService.findAll()
                .stream()
                .map(worker -> workerTransformer.apply(worker))
                .collect(Collectors.toList());
    }

    public void create(@RequestBody WorkerDto workerDto) {
        workerGenericService.create(workerDtoTransformer.apply(workerDto));
    }

    public WorkerDto update(@RequestBody WorkerDto workerDto) {
        return workerTransformer.apply(workerGenericService.update(workerDtoTransformer.apply(workerDto)));
    }

    public void deleteById(@PathVariable(value = "id") @NotNull Long id) {
        workerGenericService.deleteById(id);
    }
}
