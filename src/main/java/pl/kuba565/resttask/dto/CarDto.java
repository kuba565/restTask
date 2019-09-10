package pl.kuba565.resttask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
public class CarDto extends BaseDto {
    private Long id;
    private Integer weight;
    private Integer numberOfSeats;
    private String registrationNumber;
    private LogDto logDto;

    public CarDto(Integer weight, Integer numberOfSeats, String registrationNumber) {
        this.weight = weight;
        this.numberOfSeats = numberOfSeats;
        this.registrationNumber = registrationNumber;
    }

    public CarDto(Integer weight, Integer numberOfSeats, String registrationNumber, LogDto logDto) {
        this.weight = weight;
        this.numberOfSeats = numberOfSeats;
        this.registrationNumber = registrationNumber;
        this.logDto = logDto;
    }

    public CarDto(Long id, Integer weight, Integer numberOfSeats, String registrationNumber) {
        this.id = id;
        this.weight = weight;
        this.numberOfSeats = numberOfSeats;
        this.registrationNumber = registrationNumber;
    }

    public CarDto() {
    }

    public CarDto(LogDto logDto) {
        this.logDto = logDto;
    }
}
