package pl.kuba565.resttask.repository.jooq;

import org.jooq.DSLContext;
import org.jooq.Record8;
import org.jooq.Result;
import pl.kuba565.resttask.model.Car;
import pl.kuba565.resttask.model.Worker;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static pl.kuba565.resttask.jooq.Tables.*;

public class JooqWorkerRepositoryImpl extends JooqGenericRepositoryImpl<Worker> {
    private DSLContext dslContext;

    public JooqWorkerRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Worker update(Worker entity) {
        dslContext.update(WORKER)
                .set(WORKER.NAME, entity.getName())
                .set(WORKER.SURNAME, entity.getSurname())
                .set(WORKER.PESEL, entity.getPesel())
                .where(WORKER.ID.eq(Math.toIntExact(entity.getId())))
                .execute();
        if (entity.getCar() != null) {
            dslContext.update(CAR)
                    .set(WORKER.CAR_ID, entity.getCar().getId())
                    .set(CAR.NUMBER_OF_SEATS, entity.getCar().getNumberOfSeats().shortValue())
                    .set(CAR.REGISTRATION_NUMBER, entity.getCar().getRegistrationNumber())
                    .set(CAR.WEIGHT, BigDecimal.valueOf(entity.getCar().getWeight()))
                    .set(CAR.LOG_ID, entity.getCar().getLog().getId())
                    .where(CAR.ID.eq(Math.toIntExact(entity.getId())))
                    .execute();
            if (entity.getCar().getLog().getId() != null) {
                dslContext.update(LOG)
                        .set(LOG.VALUE, entity.getCar().getLog().getValue().getBytes())
                        .where(LOG.ID.eq(Math.toIntExact(entity.getCar().getLog().getId())))
                        .execute();
            }
        }

        return findById(entity.getId());
    }

    @Override
    public void create(Worker entity) {
        dslContext.insertInto(WORKER,
                WORKER.NAME, WORKER.SURNAME, WORKER.PESEL)
                .values(entity.getName(), entity.getSurname(), entity.getPesel())
                .execute();

        if (entity.getCar() != null && entity.getCar().getWeight() != null && entity.getCar().getRegistrationNumber() != null && entity.getCar().getNumberOfSeats() != null) {
            dslContext.insertInto(CAR,
                    CAR.NUMBER_OF_SEATS, CAR.REGISTRATION_NUMBER, CAR.WEIGHT)
                    .values(
                            entity.getCar().getNumberOfSeats().shortValue(),
                            entity.getCar().getRegistrationNumber(),
                            BigDecimal.valueOf(entity.getCar().getWeight()))
                    .execute();

            if (entity.getCar().getLog() != null && entity.getCar().getLog().getValue() != null) {
                dslContext.insertInto(LOG,
                        LOG.VALUE)
                        .values(entity.getCar().getLog().getValue().getBytes())
                        .execute();
            }
        }
    }

    @Override
    public List<Worker> findAll() {
        List<Worker> workers = new ArrayList<>();

        Result<Record8<Integer, String, String, String, Integer, BigDecimal, String, Short>> results = dslContext
                .select(
                        WORKER.ID,
                        WORKER.NAME,
                        WORKER.SURNAME,
                        WORKER.PESEL,
                        CAR.ID,
                        CAR.WEIGHT,
                        CAR.REGISTRATION_NUMBER,
                        CAR.NUMBER_OF_SEATS
                )
                .from(WORKER)
                .join(CAR)
                .on(WORKER.CAR_ID.cast(String.class).eq(CAR.ID.cast(String.class)))
                .fetch();

        if (results.isEmpty()) {
            throw new NoResultException();
        }

        for (int i = 0; i < results.size(); i++) {

            Integer workerId = results.getValue(i, WORKER.ID);
            String workerName = results.getValue(i, WORKER.NAME);
            String workerSurname = results.getValue(i, WORKER.SURNAME);
            String workerPesel = results.getValue(i, WORKER.PESEL);

            Integer carId = results.getValue(i, CAR.ID);
            Short carNumberOfSeats = results.getValue(i, CAR.NUMBER_OF_SEATS);
            String carRegistrationNumber = results.getValue(i, CAR.REGISTRATION_NUMBER);
            BigDecimal carWeight = results.getValue(i, CAR.WEIGHT);

            Car car = new Car(carId.longValue(), carWeight.intValue(), carNumberOfSeats.intValue(), carRegistrationNumber);
            Worker worker = new Worker(workerId.longValue(), car, workerPesel, workerName, workerSurname);

            workers.add(worker);
        }
        return workers;
    }

    @Override
    public void deleteById(Long id) {
        dslContext.delete(WORKER).where(WORKER.ID.eq(Math.toIntExact(id))).execute();
    }

    @Override
    public boolean exists(Long id) {
        return !dslContext.select().where(WORKER.ID.eq(Math.toIntExact(id))).fetch().isEmpty();
    }

    @Override
    public Worker findById(Long id) {
        Result<Record8<Integer, String, String, String, Integer, BigDecimal, String, Short>> result = dslContext
                .select(
                        WORKER.ID,
                        WORKER.NAME,
                        WORKER.SURNAME,
                        WORKER.PESEL,
                        CAR.ID,
                        CAR.WEIGHT,
                        CAR.REGISTRATION_NUMBER,
                        CAR.NUMBER_OF_SEATS
                )
                .from(WORKER)
                .join(CAR)
                .on(WORKER.CAR_ID.cast(String.class).eq(CAR.ID.cast(String.class)))
                .where(WORKER.ID.eq(Math.toIntExact(id)))
                .fetch();

        if (result.isEmpty()) {
            throw new NoResultException();
        }

        Integer workerId = result.getValue(0, WORKER.ID);
        String workerName = result.getValue(0, WORKER.NAME);
        String workerSurname = result.getValue(0, WORKER.SURNAME);
        String workerPesel = result.getValue(0, WORKER.PESEL);

        Integer carId = result.getValue(0, CAR.ID);
        BigDecimal carWeight = result.getValue(0, CAR.WEIGHT);
        String carRegistrationNumber = result.getValue(0, CAR.REGISTRATION_NUMBER);
        Short carNumberOfSeats = result.getValue(0, CAR.NUMBER_OF_SEATS);

        Car car = new Car(carId.longValue(), carWeight.intValue(), carNumberOfSeats.intValue(), carRegistrationNumber);

        return new Worker(workerId.longValue(), car, workerPesel, workerName, workerSurname);
    }
}
