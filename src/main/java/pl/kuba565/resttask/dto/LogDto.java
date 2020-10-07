package pl.kuba565.resttask.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class LogDto extends BaseDto {
    private Long id;
    private String value;

    public LogDto(String value) {
        this.value = value;
    }
}
