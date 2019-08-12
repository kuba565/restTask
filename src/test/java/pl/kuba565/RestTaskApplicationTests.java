package pl.kuba565;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTaskApplicationTests {
    @Autowired
    private RestTaskApplication restTaskApplication;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(restTaskApplication);
    }

}
