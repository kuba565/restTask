package pl.kuba565.util;

import pl.kuba565.resttask.model.Car;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarAssertionUtil {

    public static void assertCarFetching(Car expectedCar, Car comparedCar) {
        assertAll(
                () -> assertNotNull(comparedCar.getId()),
                () -> assertEquals(expectedCar.getNumberOfSeats(), comparedCar.getNumberOfSeats()),
                () -> assertEquals(expectedCar.getRegistrationNumber(), comparedCar.getRegistrationNumber()),
                () -> assertEquals(expectedCar.getWeight(), comparedCar.getWeight()),
                () -> assertNull(comparedCar.getLog())
        );
    }

    public static void assertCarsFetching(List<Car> expectedCars, List<Car> comparedCars) {
        assertAll(() -> {
            for (int i = 0; i < expectedCars.size(); i++) {
                assertCarFetching(expectedCars.get(i), comparedCars.get(i));
            }
        });
    }
}
