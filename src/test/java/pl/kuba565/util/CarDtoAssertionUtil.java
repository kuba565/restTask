package pl.kuba565.util;

import pl.kuba565.resttask.dto.CarDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarDtoAssertionUtil {

    public static void assertCarDtoFetching(CarDto expectedCarDto, CarDto comparedCarDto) {
        assertAll(
                () -> assertNotNull(comparedCarDto.getId()),
                () -> assertEquals(expectedCarDto.getNumberOfSeats(), comparedCarDto.getNumberOfSeats()),
                () -> assertEquals(expectedCarDto.getRegistrationNumber(), comparedCarDto.getRegistrationNumber()),
                () -> assertEquals(expectedCarDto.getWeight(), comparedCarDto.getWeight()),
                () -> assertNull(comparedCarDto.getLogDto())
        );
    }

    public static void assertCarDtosFetching(List<CarDto> expectedCarDtos, List<CarDto> comparedCarDtos) {
        assertAll(() -> {
            for (int i = 0; i < expectedCarDtos.size(); i++) {
                assertCarDtoFetching(expectedCarDtos.get(i), comparedCarDtos.get(i));
            }
        });
    }
}
