package pl.kuba565.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.kuba565.model.Worker;
import pl.kuba565.service.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/")
@Validated
public class WorkerController implements Controller<Worker> {
    private final Service<Worker> workerService;

    public WorkerController(Service<Worker> workerService) {
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
    Long post(Worker worker) {
        return workerService.create(worker);
    }

    @PutMapping("worker")
    public @ResponseBody
    void put(Worker worker) {
        workerService.update(worker);
    }

    @DeleteMapping("worker/{id}")
    public @ResponseBody
    void deleteById(@PathVariable(value = "id") @NotNull Long id) {
        workerService.deleteById(id);
    }
}
