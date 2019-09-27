package pl.kuba565.resttask.validator;

import org.springframework.validation.Errors;
import pl.kuba565.resttask.model.User;
import pl.kuba565.resttask.repository.hibernate.UserRepositoryImpl;

public class UserValidator implements GenericValidator<User> {
    private final UserRepositoryImpl userRepository;

    public UserValidator(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Errors validateOnDelete(Long id) {
        return null;
    }

    @Override
    public Errors validateOnCreate(User entity) {
        return null;
    }
}
