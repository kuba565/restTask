package pl.kuba565.resttask.validator;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.repository.CarRepositoryImpl;
import pl.kuba565.resttask.util.StringUtil;

public class CarValidator implements GenericValidator<Car> {
    private final CarRepositoryImpl carRepositoryImpl;

    public CarValidator(CarRepositoryImpl carRepositoryImpl) {
        this.carRepositoryImpl = carRepositoryImpl;
    }

    @Override
    public Errors validateOnDelete(Long id) {
        Errors errors = new BeanPropertyBindingResult(id, StringUtil.EMPTY);

        if (!carRepositoryImpl.exists(id)) {
            errors.reject(String.format("Car with id = %s does not exist!", id));
            return errors;
        }

        Long assignedWorkersCount = carRepositoryImpl.countAssignedWorkers(id);

        if (assignedWorkersCount > 0) {
            errors.reject(String.format("Car with given id has %s worker(s) assigned", assignedWorkersCount));
        }

        return errors;
    }

    @Override
    public Errors validateOnCreate(Car car) {
        Errors errors = new BeanPropertyBindingResult(car, StringUtil.EMPTY);

        if (car.getLog() == null) {
            errors.reject("Log is null");
        }

        return errors;
    }
}
