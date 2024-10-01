package arden.java.kudago.event;

import arden.java.kudago.client.CategoryRestTemplate;
import arden.java.kudago.client.LocationRestTemplate;
import arden.java.kudago.dto.Category;
import arden.java.kudago.dto.Location;
import arden.java.kudago.exception.GeneralException;
import arden.java.kudago.repository.StorageRepository;
import configuration.annotation.logtimexec.LogTimeExec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StartupLogic {
    private final CategoryRestTemplate categoryRestTemplate;
    private final LocationRestTemplate locationRestTemplate;
    private final StorageRepository<Long, Category> categoryRepository;
    private final StorageRepository<String, Location> locationStorage;

    @LogTimeExec
    @EventListener(ApplicationReadyEvent.class)
    public void startup() {
        log.info("Starting up, preparing to fill the data sources");

        fillDBWithCategories();

        fillDBWithLocations();

        log.info("Finished startup, sources should be updated now");
    }

    private void fillDBWithCategories() {
        if (categoryRestTemplate.getAllCategories().isPresent()) {
            List<Category> categories = categoryRestTemplate.getAllCategories().get();
            categories.forEach(category -> categoryRepository.create(category.id(), category));
            log.info("All categories are saved to DB");
        } else {
            log.info("Problems with saving categories to DB");
            throw new GeneralException("Categories were not found");
        }
    }

    private void fillDBWithLocations() {
        if (locationRestTemplate.getLocations().isPresent()) {
            List<Location> locations = locationRestTemplate.getLocations().get();
            locations.forEach(location -> locationStorage.create(location.slug(), location));
            log.info("All locations are saved to DB");
        } else {
            log.error("Problems with saving locations to DB");
            throw new GeneralException("No locations found");
        }
    }

}
