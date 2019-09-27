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
    private char[] password;

    public UserDto(String name, char[] password) {
        this.name = name;
        this.password = password;
    }
}
