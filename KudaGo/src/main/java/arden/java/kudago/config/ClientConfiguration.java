package arden.java.kudago.config;

import arden.java.kudago.client.CategoryRestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class ClientConfiguration {
    private final RestTemplate restTemplate;
    private final UrlConfig urlConfig;

    @Bean
    public CategoryRestTemplate categoryRestTemplate() {
        return new CategoryRestTemplate(restTemplate, urlConfig);
    }
}
