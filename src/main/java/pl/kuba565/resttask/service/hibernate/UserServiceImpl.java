package pl.kuba565.resttask.service.hibernate;

import pl.kuba565.resttask.model.User;
import pl.kuba565.resttask.repository.GenericRepository;
import pl.kuba565.resttask.service.GenericServiceImpl;
import pl.kuba565.resttask.validator.GenericValidator;

import java.util.List;

public class UserServiceImpl extends GenericServiceImpl<User> {
    private GenericRepository<User> userRepository;

    public UserServiceImpl(GenericRepository<User> userRepository,
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

//    public Boolean logIn(String name, byte[] password) {
//        User user = userRepository.findByName(name);
//        String hashed = BCrypt.hashpw(Arrays.toString(password), BCrypt.gensalt());
//        return BCrypt.checkpw(Arrays.toString(user.getPassword()), hashed);
//    }
}
