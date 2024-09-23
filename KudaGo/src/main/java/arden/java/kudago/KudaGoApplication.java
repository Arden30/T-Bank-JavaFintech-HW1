package arden.java.kudago;

import arden.java.kudago.config.UrlConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({UrlConfig.class})
public class KudaGoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KudaGoApplication.class, args);
    }

}
