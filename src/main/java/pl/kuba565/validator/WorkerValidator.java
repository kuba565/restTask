package pl.kuba565.validator;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import pl.kuba565.repository.WorkerRepository;

public class WorkerValidator implements Validator {
    private final WorkerRepository workerRepository;

    public WorkerValidator(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public Errors validateOnDelete(Long id) {
        Errors errors = new BeanPropertyBindingResult(id, "");

        if (!workerRepository.checkIfExists(id)) {
            errors.reject(String.format("Worker with id = %s does not exist!", id));
        }

        return errors;
    }
}
