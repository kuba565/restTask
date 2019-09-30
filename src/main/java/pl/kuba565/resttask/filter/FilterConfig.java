package pl.kuba565.resttask.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    AuthFilter authFilter() {
        return new AuthFilter();
    }
}
