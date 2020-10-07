package pl.kuba565.resttask.transformer.dto;

import pl.kuba565.resttask.dto.CarDto;
import pl.kuba565.resttask.model.Car;

import java.util.function.Function;

public class CarDtoTransformerImpl extends GenericDtoTransformerImpl<CarDto, Car> implements Function<CarDto, Car> {
    private LogDtoTransformerImpl logDtoTransformerImpl;

    public CarDtoTransformerImpl(LogDtoTransformerImpl logDtoTransformerImpl) {
        this.logDtoTransformerImpl = logDtoTransformerImpl;
    }

    @Override
    public Car apply(CarDto carDto) {
        Car.CarBuilder carBuilder = Car.builder()
                .id(carDto.getId())
                .registrationNumber(carDto.getRegistrationNumber())
                .weight(carDto.getWeight())
                .numberOfSeats(carDto.getNumberOfSeats());
        if (carDto.getLogDto() != null) {
            carBuilder.log(logDtoTransformerImpl.apply(carDto.getLogDto()));
        }
        return carBuilder.build();
    }
}
