package arden.java.kudago.config;

import arden.java.kudago.dto.Category;
import arden.java.kudago.dto.Location;
import arden.java.kudago.repository.StorageRepository;
import arden.java.kudago.repository.impl.StorageRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {
    @Bean
    public StorageRepository<Long, Category> categoryRepository() {
        return new StorageRepositoryImpl<>();
    }

    @Bean
    public StorageRepository<String, Location> locationRepository() {
        return new StorageRepositoryImpl<>();
    }
}
