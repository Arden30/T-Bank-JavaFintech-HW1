package arden.java.kudago.service.impl;

import arden.java.kudago.dto.Location;
import arden.java.kudago.repository.StorageRepository;
import arden.java.kudago.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationServiceImpl implements LocationService {
    private final StorageRepository<String, Location> locationStorage;

    @Override
    public List<Location> getAllLocations() {
        return locationStorage.readAll();
    }

    @Override
    public Location getLocationBySlug(String slug) {
        return locationStorage.read(slug);
    }

    @Override
    public Location createLocation(Location Location) {
        return locationStorage.create(Location.slug(), Location);
    }

    @Override
    public Location updateLocation(String slug, Location Location) {
        return locationStorage.update(slug, Location);
    }

    @Override
    public boolean deleteLocation(String slug) {
        return locationStorage.delete(slug);
    }
}
