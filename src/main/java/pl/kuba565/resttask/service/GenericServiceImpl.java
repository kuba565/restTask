package pl.kuba565.resttask.service;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import pl.kuba565.resttask.exception.ValidationException;
import pl.kuba565.resttask.model.BaseModel;
import pl.kuba565.resttask.repository.GenericRepository;
import pl.kuba565.resttask.validator.GenericValidator;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class GenericServiceImpl<T extends BaseModel> implements GenericService<T> {
    private final GenericRepository<T> genericRepository;
    private final GenericValidator<T> genericValidator;

    public GenericServiceImpl(GenericRepository<T> genericRepository,
                              GenericValidator<T> genericValidator) {
        this.genericRepository = genericRepository;
        this.genericValidator = genericValidator;
    }

    public T update(T entity) {
        return genericRepository.update(entity);
    }

    public void create(T entity) {
        Errors errors = genericValidator.validateOnCreate(entity);
        if (errors != null) {
            List<ObjectError> allErrors = errors.getAllErrors();

            if (!allErrors.isEmpty()) {
                throw new ValidationException(allErrors);
            }
        }

        genericRepository.create(entity);
    }

    public void deleteById(Long id) {
        Errors errors = genericValidator.validateOnDelete(id);
        if (errors != null) {
            List<ObjectError> allErrors = errors.getAllErrors();

            if (!allErrors.isEmpty()) {
                throw new ValidationException(allErrors);
            }
        }

        genericRepository.deleteById(id);
    }
}
