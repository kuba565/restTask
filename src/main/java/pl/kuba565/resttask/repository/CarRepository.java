package pl.kuba565.resttask.repository;

public interface CarRepository extends GenericRepository {
    Long countAssignedWorkers(Long id);
}
