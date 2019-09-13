package pl.kuba565.resttask.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.AbstractTest;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.model.Log;
import pl.kuba565.resttask.service.CarServiceImpl;
import pl.kuba565.util.CarAssertionUtil;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static pl.kuba565.util.CarAssertionUtil.assertCarsFetching;

@Transactional
public class JooqCarRepositoryImplTest extends AbstractTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JooqCarRepositoryImpl carRepository;

    @Autowired
    private CarServiceImpl carServiceImpl;

    private List<Car> exampleCars;

    private Car exampleCar;

    private Long carId;

    @BeforeEach
    public void init() {
        exampleCars = carServiceImpl.findAll();
        if (exampleCars.isEmpty()) {
            carServiceImpl.create(new Car(new Log("test"), 1221, 4, "1234"));
            exampleCars = carServiceImpl.findAll();
            exampleCar = exampleCars.get(0);
            carId = exampleCar.getId();
        }
        exampleCar = exampleCars.get(0);
        carId = exampleCar.getId();
    }

    @Test
    public void shouldFindAllCarsWithoutLogField() {
        //when
        List<Car> result = carRepository.findAll();

        //then
        assertCarsFetching(exampleCars, result);
    }

    @Test
    public void shouldFindCarWithoutLogField() {
        //when
        Car result = carRepository.findById(carId);

        //then
        CarAssertionUtil.assertCarFetching(exampleCar, result);
    }

}
