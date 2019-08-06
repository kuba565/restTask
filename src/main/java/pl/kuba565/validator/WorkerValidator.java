package pl.kuba565.validator;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import pl.kuba565.model.Worker;
import pl.kuba565.repository.Repository;

public class WorkerValidator implements Validator {
    private final Repository<Worker> workerRepository;

    public WorkerValidator(Repository<Worker> workerRepository) {
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
