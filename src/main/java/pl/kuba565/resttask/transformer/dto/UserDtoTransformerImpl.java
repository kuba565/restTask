package pl.kuba565.resttask.transformer.dto;

import pl.kuba565.resttask.dto.UserDto;
import pl.kuba565.resttask.model.User;

import java.util.function.Function;

public class UserDtoTransformerImpl extends GenericDtoTransformerImpl<UserDto, User> implements Function<UserDto, User> {
    public UserDtoTransformerImpl() {
    }

    @Override
    public User apply(UserDto userDto) {
        User.UserBuilder userBuilder = User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .password(userDto.getPassword());
        return userBuilder.build();
    }
}
