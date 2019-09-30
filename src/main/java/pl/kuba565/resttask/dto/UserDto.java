package pl.kuba565.resttask.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UserDto extends BaseDto {
    private Long id;
    private String name;
    private String password;

    public UserDto(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
