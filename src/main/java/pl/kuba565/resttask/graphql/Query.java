package pl.kuba565.resttask.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.model.Worker;
import pl.kuba565.resttask.repository.CarRepositoryImpl;
import pl.kuba565.resttask.repository.WorkerRepositoryImpl;

import java.util.List;

public class Query implements GraphQLQueryResolver {
    private CarRepositoryImpl carRepository;
    private WorkerRepositoryImpl workerRepository;

    public Query(CarRepositoryImpl carRepository, WorkerRepositoryImpl workerRepository) {
        this.carRepository = carRepository;
        this.workerRepository = workerRepository;
    }

    public List<Car> getCars(Integer count) {
        return carRepository.findAll().subList(0, count);
    }

    public Car getCar(Long id) {
        return carRepository.findById(id);
    }

    public List<Worker> getWorkers(Integer count) {
        return workerRepository.findAll().subList(0, count);
    }

    public Worker getWorker(Long id) {
        return workerRepository.findById(id);
    }
}
