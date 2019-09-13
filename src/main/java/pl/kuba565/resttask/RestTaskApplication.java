package pl.kuba565.resttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestTaskApplication.class, args);
    }

}
