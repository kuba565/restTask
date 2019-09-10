package pl.kuba565.util;

import org.hibernate.Hibernate;
import pl.kuba565.resttask.model.Worker;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorkerAssertionUtil {
    public static void assertWorkerFetching(Worker expectedWorker, Worker comparedWorker) {
        assertAll(
                () -> assertNotNull(comparedWorker.getId()),
                () -> assertEquals(expectedWorker.getName(), comparedWorker.getName()),
                () -> assertEquals(expectedWorker.getPesel(), comparedWorker.getPesel()),
                () -> assertEquals(expectedWorker.getSurname(), comparedWorker.getSurname()),
                () -> assertTrue(Hibernate.isInitialized(comparedWorker.getCar().getLog()))
        );
    }

    public static void assertWorkersFetching(List<Worker> expected, List<Worker> result) {
        assertAll(() -> {
            for (int i = 0; i < result.size(); i++) {
                assertWorkerFetching(expected.get(i), result.get(i));
            }
        });
    }
}
