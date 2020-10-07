package pl.kuba565.resttask.transformer.dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.AbstractTest;
import pl.kuba565.resttask.dto.CarDto;
import pl.kuba565.resttask.dto.LogDto;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.model.Log;

import static org.junit.jupiter.api.Assertions.*;

class CarDtoTransformerImplTest extends AbstractTest {
    @Autowired
    private CarDtoTransformerImpl carDtoTransformer;

    @Test
    public void shouldTransformCarDtoToCar() {
        //given
        CarDto carDto = new CarDto(1, 5, "", new LogDto("test"));
        Car expectedCar = new Car(new Log("test"), 1, 5, "");

        //when
        Car car = carDtoTransformer.apply(carDto);

        //then
        assertAll(
                () -> assertNull(car.getId()),
                () -> assertEquals(expectedCar.getNumberOfSeats(), car.getNumberOfSeats()),
                () -> assertEquals(expectedCar.getRegistrationNumber(), car.getRegistrationNumber()),
                () -> assertEquals(expectedCar.getWeight(), car.getWeight()),
                () -> assertEquals(expectedCar.getLog().getId(), car.getLog().getId()),
                () -> assertEquals(expectedCar.getLog().getValue(), car.getLog().getValue())
        );
    }

    @Test
    public void shouldTransformCarDtoToCarWithNullLog() {
        //given
        CarDto carDto = new CarDto(1, 5, "");
        Car expectedCar = new Car(1, 5, "");

        //when
        Car car = carDtoTransformer.apply(carDto);

        //then
        assertAll(
                () -> assertNull(car.getId()),
                () -> assertEquals(car.getNumberOfSeats(), expectedCar.getNumberOfSeats()),
                () -> assertEquals(car.getRegistrationNumber(), expectedCar.getRegistrationNumber()),
                () -> assertEquals(car.getWeight(), expectedCar.getWeight()),
                () -> assertNull(car.getLog())
        );
    }

    @Test
    public void shouldTransformCarDtoToCarWithAllFieldsNull() {
        //given
        CarDto carDto = new CarDto();

        //when
        Car car = carDtoTransformer.apply(carDto);

        //then
        assertAll(
                () -> assertNotNull(car),
                () -> assertNull(car.getId()),
                () -> assertNull(car.getNumberOfSeats()),
                () -> assertNull(car.getRegistrationNumber()),
                () -> assertNull(car.getWeight()),
                () -> assertNull(car.getLog())
        );
    }
}