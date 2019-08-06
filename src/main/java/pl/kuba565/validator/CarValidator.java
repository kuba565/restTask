package pl.kuba565.validator;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import pl.kuba565.model.Car;
import pl.kuba565.repository.Repository;

public class CarValidator implements Validator {
    private final Repository<Car> carRepository;

    public CarValidator(Repository<Car> carRepository) {
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
