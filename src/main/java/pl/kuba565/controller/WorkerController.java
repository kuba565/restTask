package pl.kuba565.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kuba565.model.Worker;
import pl.kuba565.service.WorkerService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/")
@Validated
public class WorkerController implements Controller<Worker> {
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("worker/{id}")
    public @ResponseBody
    Worker findById(@PathVariable(value = "id") @NotNull Long id) {
        return workerService.findById(id);
    }

    @GetMapping("worker")
    public @ResponseBody
    List<Worker> findAll() {
        return workerService.findAll();
    }

    @PostMapping("worker")
    public @ResponseBody
    Long post(Worker newWorker) {
        return workerService.create(newWorker);
    }

    @PutMapping("worker")
    public @ResponseBody
    Worker put(Worker newWorker) {
        return workerService.update(newWorker);
    }

    @DeleteMapping("worker/{id}")
    public @ResponseBody
    void deleteById(@PathVariable(value = "id") @NotNull Long id) {
        workerService.deleteById(id);
    }
}
