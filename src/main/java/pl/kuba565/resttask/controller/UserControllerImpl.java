package pl.kuba565.resttask.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kuba565.resttask.dto.UserDto;
import pl.kuba565.resttask.model.User;
import pl.kuba565.resttask.service.GenericService;
import pl.kuba565.resttask.transformer.dto.UserDtoTransformerImpl;
import pl.kuba565.resttask.transformer.model.UserTransformerImpl;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static pl.kuba565.resttask.util.StringUtil.USER;

@RequestMapping(USER)
@Validated
public class UserControllerImpl extends GenericControllerImpl<User, UserDto> implements UserController {
    private GenericService<User> userService;
    private UserDtoTransformerImpl userDtoTransformer;
    private UserTransformerImpl userTransformer;

    public UserControllerImpl(GenericService<User> userService,
                              UserDtoTransformerImpl userDtoTransformer,
                              UserTransformerImpl userTransformer) {
        super(userService, userDtoTransformer, userTransformer);
        this.userService = userService;
        this.userDtoTransformer = userDtoTransformer;
        this.userTransformer = userTransformer;
    }

    @Override
    public UserDto findById(@NotNull Long id) {
        return userTransformer.apply(userService.findById(id));
    }

    @Override
    public List<UserDto> findAll() {
        return userService.findAll()
                .stream()
                .map(user -> userTransformer.apply(user))
                .collect(Collectors.toList());
    }

    @Override
    public void create(@RequestBody UserDto dto) {
        userService.create(userDtoTransformer.apply(dto));
    }

    @Override
    public UserDto update(@RequestBody UserDto dto) {
        return userTransformer.apply(
                userService.update(
                        userDtoTransformer.apply(dto)));
    }

    @Override
    public void deleteById(@PathVariable(value = "id") @NotNull Long id) {
        userService.deleteById(id);
    }
}
