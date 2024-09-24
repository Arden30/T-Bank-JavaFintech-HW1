package arden.java.kudago.starter;

import arden.java.kudago.dto.Category;
import arden.java.kudago.dto.Location;
import arden.java.kudago.repository.StorageRepository;
import arden.java.kudago.service.CategoryService;
import arden.java.kudago.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StartupLogic {
    private final CategoryService categoryService;
    private final LocationService locationService;

    @EventListener(ApplicationReadyEvent.class)
    public void startup() {
        log.info("Starting up, preparing to fill the data sources");

        categoryService.getAllCategories();

        locationService.getAllLocations();

        log.info("Finished startup, sources should be updated now");
    }
}
