package pl.kuba565.resttask.transformer.model;

import org.hibernate.Hibernate;
import pl.kuba565.resttask.dto.CarDto;
import pl.kuba565.resttask.model.Car;

import java.util.function.Function;

public class CarTransformerImpl extends GenericModelTransformerImpl<Car, CarDto> implements Function<Car, CarDto> {
    private LogTransformerImpl logToLogDtoTransformerImpl;

    public CarTransformerImpl(LogTransformerImpl logToLogDtoTransformerImpl) {
        this.logToLogDtoTransformerImpl = logToLogDtoTransformerImpl;
    }

    @Override
    public CarDto apply(Car car) {
        CarDto.CarDtoBuilder carDtoBuilder = CarDto.builder()
                .id(car.getId())
                .registrationNumber(car.getRegistrationNumber())
                .weight(car.getWeight())
                .numberOfSeats(car.getNumberOfSeats());
        if (Hibernate.isInitialized(car.getLog()) && car.getLog() != null) {
            carDtoBuilder.logDto(logToLogDtoTransformerImpl.apply(car.getLog()));
        }
        return carDtoBuilder.build();
    }
}
