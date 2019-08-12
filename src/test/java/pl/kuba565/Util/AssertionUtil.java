package pl.kuba565.Util;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.Assertions;
import pl.kuba565.model.Worker;

import java.util.List;

public class AssertionUtil {

    public static void compareWorker(Worker expectedWorker, Worker worker) {
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedWorker.getId(), worker.getId()),
                () -> Assertions.assertEquals(expectedWorker.getName(), worker.getName()),
                () -> Assertions.assertEquals(expectedWorker.getPesel(), worker.getPesel()),
                () -> Assertions.assertEquals(expectedWorker.getSurname(), worker.getSurname()),
                () -> Assertions.assertEquals(expectedWorker.getCar().getId(), worker.getCar().getId()),
                () -> Assertions.assertEquals(expectedWorker.getCar().getNumberOfSeats(), worker.getCar().getNumberOfSeats()),
                () -> Assertions.assertEquals(expectedWorker.getCar().getRegistrationNumber(), worker.getCar().getRegistrationNumber()),
                () -> Assertions.assertEquals(expectedWorker.getCar().getWeight(), worker.getCar().getWeight()),
                () -> Assertions.assertFalse(Hibernate.isInitialized(worker.getCar().getLog()))
        );
    }

    public static void compareWorkers(List<Worker> expected, List<Worker> result) {
        Assertions.assertAll(
                () -> {
                    for (int i = 0; i < result.size(); i++) {
                        compareWorker(expected.get(i), result.get(i));
                    }
                }
        );
    }
}
