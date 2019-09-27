package pl.kuba565.resttask.transformer.model;

import pl.kuba565.resttask.dto.UserDto;
import pl.kuba565.resttask.model.User;

import java.util.function.Function;

public class UserTransformerImpl extends GenericModelTransformerImpl<User, UserDto> implements Function<User, UserDto> {

    public UserTransformerImpl() {
    }

    @Override
    public UserDto apply(User user) {
        UserDto.UserDtoBuilder userDtoBuilder = UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword());
        return userDtoBuilder.build();
    }
}
