package pl.kuba565.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import pl.kuba565.exception.ValidationException;
import pl.kuba565.model.Worker;
import pl.kuba565.repository.WorkerRepository;
import pl.kuba565.validator.WorkerValidator;

import java.util.List;

@Transactional
public class WorkerService implements Service<Worker> {
    private final WorkerValidator workerValidator;
    private final WorkerRepository workerRepository;

    WorkerService(WorkerRepository workerRepository, WorkerValidator workerValidator) {
        this.workerRepository = workerRepository;
        this.workerValidator = workerValidator;
    }

    public Worker update(Worker newWorker) {
        return workerRepository.update(newWorker);
    }

    public Long create(Worker newWorker) {
        return workerRepository.create(newWorker);
    }

    public void deleteById(Long id) {
        Errors errors = workerValidator.validateOnDelete(id);

        List<ObjectError> allErrors = errors.getAllErrors();
        if (allErrors.size() > 0) {
            throw new ValidationException(allErrors);
        }

        workerRepository.deleteById(id);
    }

    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    public Worker findById(Long id) {
        return workerRepository.findById(id);
    }
}
