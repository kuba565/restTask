package pl.kuba565.resttask.repository;

import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Record4;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kuba565.resttask.model.Car;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static pl.kuba565.resttask.jooq.Tables.CAR;

public class JooqCarRepositoryImpl extends GenericRepositoryImpl<Car> {
    @Autowired
    private DSLContext dslContext;

    public JooqCarRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();

        Result<Record4<Integer, Short, String, BigDecimal>> results = dslContext.select(
                CAR.ID,
                CAR.NUMBER_OF_SEATS,
                CAR.REGISTRATION_NUMBER,
                CAR.WEIGHT)
                .from(CAR)
                .fetch();

        for (int i = 0; i < results.size(); i++) {

            Integer id = results.getValue(i, CAR.ID);
            Short numberOfSeats = results.getValue(i, CAR.NUMBER_OF_SEATS);
            String registrationNumber = results.getValue(i, CAR.REGISTRATION_NUMBER);
            BigDecimal weight = results.getValue(i, CAR.WEIGHT);

            cars.add(new Car(id.longValue(), weight.intValue(), Integer.valueOf(numberOfSeats), registrationNumber));
        }
        return cars;
    }

    @Override
    public Car findById(Long id) {
        Result<Record3<Short, String, BigDecimal>> result = dslContext.select(
                CAR.NUMBER_OF_SEATS,
                CAR.REGISTRATION_NUMBER,
                CAR.WEIGHT)
                .from(CAR)
                .where(CAR.ID.eq(Math.toIntExact(id)))
                .fetch();

        Short numberOfSeats = result.getValue(0, CAR.NUMBER_OF_SEATS);
        String registrationNumber = result.getValue(0, CAR.REGISTRATION_NUMBER);
        BigDecimal weight = result.getValue(0, CAR.WEIGHT);

        return new Car(id, weight.intValue(), Integer.valueOf(numberOfSeats), registrationNumber);
    }
}
