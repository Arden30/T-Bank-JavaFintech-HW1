package arden.java.kudago.service.impl;

import arden.java.kudago.client.LocationRestTemplate;
import arden.java.kudago.dto.Location;
import arden.java.kudago.exception.GeneralException;
import arden.java.kudago.repository.StorageRepository;
import arden.java.kudago.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRestTemplate locationRestTemplate;
    private final StorageRepository<String, Location> locationStorage;

    @Override
    public List<Location> getAllLocations() {
        if (locationRestTemplate.getLocations().isPresent()) {
            List<Location> locations = locationRestTemplate.getLocations().get();
            locations.forEach(location -> locationStorage.create(location.slug(), location));

            return locations;
        }

        throw new GeneralException("No locations found");
    }

    @Override
    public Location getLocationBySlug(String slug) {
        if (locationRestTemplate.getLocation(slug).isPresent()) {
            Location location = locationRestTemplate.getLocation(slug).get();
            locationStorage.create(location.slug(), location);

            return location;
        }

        throw new GeneralException("No location found");
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
