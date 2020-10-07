package pl.kuba565.resttask.validator;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import pl.kuba565.resttask.model.Worker;
import pl.kuba565.resttask.repository.WorkerRepositoryImpl;
import pl.kuba565.resttask.util.StringUtil;

public class WorkerValidator implements GenericValidator<Worker> {
    private final WorkerRepositoryImpl workerRepositoryImpl;

    public WorkerValidator(WorkerRepositoryImpl workerRepositoryImpl) {
        this.workerRepositoryImpl = workerRepositoryImpl;
    }

    @Override
    public Errors validateOnDelete(Long id) {
        Errors errors = new BeanPropertyBindingResult(id, StringUtil.EMPTY);

        if (!workerRepositoryImpl.exists(id)) {
            errors.reject(String.format("Worker with id = %s does not exist!", id));
        }

        if (id < 0) {
            errors.reject(String.format("Illegal id: %s", id));
        }

        return errors;
    }

    @Override
    public Errors validateOnCreate(Worker worker) {
        Errors errors = new BeanPropertyBindingResult(worker, StringUtil.EMPTY);

        if (worker.getId() != null) {
            errors.reject("Worker id is not null");
        }

        return errors;
    }
}
