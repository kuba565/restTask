package pl.kuba565.resttask.service.hibernate;

import org.mindrot.jbcrypt.BCrypt;
import pl.kuba565.resttask.model.User;
import pl.kuba565.resttask.repository.hibernate.UserRepositoryImpl;
import pl.kuba565.resttask.service.GenericServiceImpl;
import pl.kuba565.resttask.service.UserService;
import pl.kuba565.resttask.validator.GenericValidator;

import java.util.Arrays;
import java.util.List;

public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {
    private UserRepositoryImpl userRepository;

    public UserServiceImpl(UserRepositoryImpl userRepository,
                           GenericValidator<User> userGenericValidator) {
        super(userRepository, userGenericValidator);
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void create(User user) {
        String hashedPassword = BCrypt.hashpw(String.valueOf(user.getPassword()), BCrypt.gensalt());
        userRepository.create(new User(user.getName(), hashedPassword));
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
