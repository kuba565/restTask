package pl.kuba565.resttask.service;

import pl.kuba565.resttask.model.User;

public interface UserService extends GenericService<User> {
    User findByName(String name);
}
