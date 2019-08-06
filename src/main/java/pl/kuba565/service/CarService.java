package pl.kuba565.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import pl.kuba565.exception.ValidationException;
import pl.kuba565.model.Car;
import pl.kuba565.repository.CarRepository;
import pl.kuba565.validator.CarValidator;

import java.util.List;

@Transactional
public class CarService implements Service<Car> {
    private final CarValidator carValidator;
    private final CarRepository carRepository;

    CarService(CarRepository carRepository, CarValidator carValidator) {
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
        if (allErrors.size() > 0) {
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
