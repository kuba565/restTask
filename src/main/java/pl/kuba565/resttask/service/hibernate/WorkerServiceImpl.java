package pl.kuba565.resttask.service.hibernate;

import pl.kuba565.resttask.model.Worker;
import pl.kuba565.resttask.repository.GenericRepository;
import pl.kuba565.resttask.service.GenericServiceImpl;
import pl.kuba565.resttask.validator.GenericValidator;

import java.util.List;

public class WorkerServiceImpl extends GenericServiceImpl<Worker> {
    private GenericRepository<Worker> workerGenericRepository;

    public WorkerServiceImpl(GenericRepository<Worker> workerGenericRepository,
                             GenericValidator<Worker> genericValidator) {
        super(workerGenericRepository, genericValidator);
        this.workerGenericRepository = workerGenericRepository;
    }

    @Override
    public List<Worker> findAll() {
        return workerGenericRepository.findAll();
    }

    @Override
    public Worker findById(Long id) {
        return workerGenericRepository.findById(id);
    }
}
