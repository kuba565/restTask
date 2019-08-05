package pl.kuba565.validator;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import pl.kuba565.repository.CarRepository;

public class CarValidator implements Validator {
    private final CarRepository carRepository;

    public CarValidator(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Errors validateOnDelete(Long id) {
        Errors errors = new BeanPropertyBindingResult(id, "");

        if (!carRepository.checkIfExists(id)) {
            errors.reject(String.format("Car with id = %s does not exist!", id));
            return errors;
        }

        Long assignedWorkersCount = carRepository.countAssignedWorkers(id);

        if (assignedWorkersCount > 0) {
            errors.reject(String.format("Car with given id has %s worker(s) assigned", assignedWorkersCount));
        }

        return errors;
    }
}
