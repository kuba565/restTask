package pl.kuba565.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import pl.kuba565.exception.ValidationException;
import pl.kuba565.model.Car;
import pl.kuba565.repository.Repository;
import pl.kuba565.validator.Validator;

import java.util.List;

@Transactional
public class CarService implements Service<Car> {
    private final Validator carValidator;
    private final Repository<Car> carRepository;

    CarService(Repository<Car> carRepository, Validator carValidator) {
        this.carRepository = carRepository;
        this.carValidator = carValidator;
    }

    public Car update(Car newCar) {
        return carRepository.update(newCar);
    }

    public Long create(Car newCar) {
        return carRepository.create(newCar);
    }

    public void deleteById(Long id) {
        Errors errors = carValidator.validateOnDelete(id);

        List<ObjectError> allErrors = errors.getAllErrors();
        if (!allErrors.isEmpty()) {
            throw new ValidationException(allErrors);
        }

        carRepository.deleteById(id);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findById(Long id) {
        return carRepository.findById(id);
    }
}
