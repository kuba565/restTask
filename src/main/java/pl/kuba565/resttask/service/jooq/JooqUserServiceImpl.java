package pl.kuba565.resttask.service.jooq;

import org.mindrot.jbcrypt.BCrypt;
import pl.kuba565.resttask.model.User;
import pl.kuba565.resttask.repository.jooq.JooqUserRepositoryImpl;
import pl.kuba565.resttask.service.GenericServiceImpl;
import pl.kuba565.resttask.service.UserService;
import pl.kuba565.resttask.validator.GenericValidator;

import java.util.List;

public class JooqUserServiceImpl extends GenericServiceImpl<User> implements UserService {
    private JooqUserRepositoryImpl userRepository;

    public JooqUserServiceImpl(JooqUserRepositoryImpl userRepository,
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
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        userRepository.create(new User(user.getName(), hashedPassword));
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
