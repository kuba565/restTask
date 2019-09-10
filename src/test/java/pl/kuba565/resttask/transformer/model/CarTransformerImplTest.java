package pl.kuba565.resttask.transformer.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.AbstractTest;
import pl.kuba565.resttask.dto.CarDto;
import pl.kuba565.resttask.dto.LogDto;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.model.Log;

import static org.junit.jupiter.api.Assertions.*;

public class CarTransformerImplTest extends AbstractTest {
    @Autowired
    private CarTransformerImpl carTransformer;

    @Test
    public void shouldTransformCarToCarDto() {
        //given
        Car car = new Car(new Log("test"), 1, 5, "");
        CarDto expectedCarDto = new CarDto(1, 5, "", new LogDto("test"));

        //when
        CarDto carDto = carTransformer.apply(car);

        //then
        assertAll(
                () -> assertNull(carDto.getId()),
                () -> assertEquals(expectedCarDto.getNumberOfSeats(), carDto.getNumberOfSeats()),
                () -> assertEquals(expectedCarDto.getRegistrationNumber(), carDto.getRegistrationNumber()),
                () -> assertEquals(expectedCarDto.getWeight(), carDto.getWeight()),
                () -> assertEquals(expectedCarDto.getLogDto().getId(), carDto.getLogDto().getId()),
                () -> assertEquals(expectedCarDto.getLogDto().getValue(), carDto.getLogDto().getValue())
        );
    }

    @Test
    public void shouldTransformCarToCarDtoWithNullLog() {
        //given
        Car car = new Car(1, 5, "");
        CarDto expectedCarDto = new CarDto(1, 5, "");

        //when
        CarDto carDto = carTransformer.apply(car);

        //then
        assertAll(
                () -> assertNull(carDto.getId()),
                () -> assertEquals(expectedCarDto.getNumberOfSeats(), carDto.getNumberOfSeats()),
                () -> assertEquals(expectedCarDto.getRegistrationNumber(), carDto.getRegistrationNumber()),
                () -> assertEquals(expectedCarDto.getWeight(), carDto.getWeight()),
                () -> assertNull(carDto.getLogDto())
        );
    }

    @Test
    public void shouldTransformCarToCarDtoWithAllFieldsNull() {
        //given
        Car car = new Car();

        //when
        CarDto carDto = carTransformer.apply(car);

        //then
        assertAll(
                () -> assertNotNull(carDto),
                () -> assertNull(carDto.getId()),
                () -> assertNull(carDto.getNumberOfSeats()),
                () -> assertNull(carDto.getRegistrationNumber()),
                () -> assertNull(carDto.getWeight()),
                () -> assertNull(carDto.getLogDto())
        );
    }
}