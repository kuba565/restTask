package pl.kuba565.resttask.repository.jooq;

import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.Record4;
import org.jooq.Result;
import pl.kuba565.resttask.model.Car;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static pl.kuba565.resttask.jooq.Tables.CAR;
import static pl.kuba565.resttask.jooq.Tables.LOG;
import static pl.kuba565.resttask.jooq.tables.Worker.WORKER;

public class JooqCarRepositoryImpl extends JooqGenericRepositoryImpl<Car> {
    private DSLContext dslContext;

    public JooqCarRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public Long countAssignedWorkers(Long id) {
        return dslContext
                .selectCount()
                .from(WORKER)
                .where(CAR.ID.eq(Math.toIntExact(id)))
                .fetchOne(0, Integer.class).longValue();
    }

    @Override
    public Car update(Car entity) {
        Integer numberOfSeats = entity.getNumberOfSeats();
        String registrationNumber = entity.getRegistrationNumber();
        BigDecimal weight = BigDecimal.valueOf(entity.getWeight());
        Long logId = entity.getLog().getId();

        dslContext.update(CAR)
                .set(CAR.NUMBER_OF_SEATS, numberOfSeats.shortValue())
                .set(CAR.REGISTRATION_NUMBER, registrationNumber)
                .set(CAR.WEIGHT, weight)
                .set(CAR.LOG_ID, logId)
                .where(CAR.ID.eq(Math.toIntExact(entity.getId())))
                .execute();
        if (entity.getLog().getId() != null) {
            dslContext.update(LOG)
                    .set(LOG.VALUE, entity.getLog().getValue().getBytes())
                    .where(LOG.ID.eq(Math.toIntExact(entity.getLog().getId())))
                    .execute();
        }

        return findById(entity.getId());
    }

    @Override
    public void create(Car entity) {
        dslContext.insertInto(CAR,
                CAR.NUMBER_OF_SEATS, CAR.REGISTRATION_NUMBER, CAR.WEIGHT, CAR.LOG_ID)
                .values(entity.getNumberOfSeats().shortValue(), entity.getRegistrationNumber(), BigDecimal.valueOf(entity.getWeight()), entity.getLog().getId())
                .execute();
        if (entity.getLog() != null) {
            dslContext.insertInto(LOG,
                    LOG.VALUE)
                    .values(entity.getLog().getValue().getBytes())
                    .execute();
        }
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();

        Result<Record4<Integer, Short, String, BigDecimal>> results = dslContext
                .select(
                        CAR.ID,
                        CAR.NUMBER_OF_SEATS,
                        CAR.REGISTRATION_NUMBER,
                        CAR.WEIGHT)
                .from(CAR)
                .fetch();

        if (results.isEmpty()) {
            throw new NoResultException();
        }

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
    public void deleteById(Long id) {
        dslContext.delete(CAR).where(CAR.ID.eq(Math.toIntExact(id))).execute();
    }

    @Override
    public boolean exists(Long id) {
        return !dslContext.select().where(CAR.ID.eq(Math.toIntExact(id))).fetch().isEmpty();
    }

    @Override
    public Car findById(Long id) {
        Result<Record3<Short, String, BigDecimal>> result = dslContext
                .select(
                        CAR.NUMBER_OF_SEATS,
                        CAR.REGISTRATION_NUMBER,
                        CAR.WEIGHT)
                .from(CAR)
                .where(CAR.ID.eq(Math.toIntExact(id)))
                .fetch();

        if (result.isEmpty()) {
            throw new NoResultException();
        }

        Short numberOfSeats = result.getValue(0, CAR.NUMBER_OF_SEATS);
        String registrationNumber = result.getValue(0, CAR.REGISTRATION_NUMBER);
        BigDecimal weight = result.getValue(0, CAR.WEIGHT);

        return new Car(id, weight.intValue(), Integer.valueOf(numberOfSeats), registrationNumber);
    }
}
