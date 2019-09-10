package pl.kuba565.resttask;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RestTaskApplicationTests {
    @Autowired
    private RestTaskApplication restTaskApplication;

    @Test
    public void contextLoads() {
        assertNotNull(restTaskApplication);
    }

}
