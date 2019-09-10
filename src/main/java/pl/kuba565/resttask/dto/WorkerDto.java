package pl.kuba565.resttask.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class WorkerDto extends BaseDto {
    private Long id;
    private CarDto carDto;
    private String pesel;
    private String name;
    private String surname;

    public WorkerDto(String pesel, String name, String surname) {
        this.pesel = pesel;
        this.name = name;
        this.surname = surname;
    }

    public WorkerDto(Long id, String pesel, String name, String surname) {
        this(pesel, name, surname);
        this.id = id;
    }

    public WorkerDto(CarDto carDto, String pesel, String name, String surname) {
        this(pesel, name, surname);
        this.carDto = carDto;
    }
}
