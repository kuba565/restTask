package pl.kuba565.resttask.service;

import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.repository.GenericRepository;
import pl.kuba565.resttask.validator.GenericValidator;

import java.util.List;

public class CarServiceImpl extends GenericServiceImpl<Car> {
    private GenericRepository<Car> carGenericRepository;

    public CarServiceImpl(GenericRepository<Car> carGenericRepository,
                          GenericValidator<Car> carGenericValidator) {
        super(carGenericRepository, carGenericValidator);
        this.carGenericRepository = carGenericRepository;
    }

    @Override
    public List<Car> findAll() {
        return carGenericRepository.findAll();
    }

    @Override
    public Car findById(Long id) {
        return carGenericRepository.findById(id);
    }
}
