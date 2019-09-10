package pl.kuba565.util;

import pl.kuba565.resttask.dto.WorkerDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WorkerDtoAssertionUtil {

    public static void assertWorkerDtoFetching(WorkerDto expectedWorkerDto, WorkerDto workerDto) {
        assertAll(
                () -> assertNotNull(workerDto.getId()),
                () -> assertEquals(expectedWorkerDto.getName(), workerDto.getName()),
                () -> assertEquals(expectedWorkerDto.getPesel(), workerDto.getPesel()),
                () -> assertEquals(expectedWorkerDto.getSurname(), workerDto.getSurname()),
                () -> {
                    if (workerDto.getCarDto() != null) {
                        assertAll(
                                () -> assertEquals(expectedWorkerDto.getCarDto().getNumberOfSeats(), workerDto.getCarDto().getNumberOfSeats()),
                                () -> assertEquals(expectedWorkerDto.getCarDto().getRegistrationNumber(), workerDto.getCarDto().getRegistrationNumber()),
                                () -> assertEquals(expectedWorkerDto.getCarDto().getWeight(), workerDto.getCarDto().getWeight()),
                                () -> assertNull(workerDto.getCarDto().getLogDto())
                        );
                    }
                }
        );
    }

    public static void assertWorkerDtosFetching(List<WorkerDto> expected, List<WorkerDto> result) {
        assertAll(() -> {
            for (int i = 0; i < result.size(); i++) {
                assertWorkerDtoFetching(expected.get(i), result.get(i));
            }
        });
    }
}
