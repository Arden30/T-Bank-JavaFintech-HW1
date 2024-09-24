package arden.java.kudago.client;

import arden.java.kudago.config.UrlConfig;
import arden.java.kudago.dto.Location;
import arden.java.kudago.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LocationRestTemplate {
    private final RestTemplate restTemplate;
    private final UrlConfig urlConfig;
    private final StorageRepository<String, Location> locationStorage;

    public Optional<List<Location>> getLocations() {
        ResponseEntity<List<Location>> response = restTemplate
                .exchange(urlConfig.url() + "/locations",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {});

        return Optional.ofNullable(response.getBody());
    }

    public Optional<Location> getLocation(String slug) {
        ResponseEntity<Location> response = restTemplate
                .exchange(urlConfig.url() + "/locations/" + slug,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {});

        return Optional.ofNullable(response.getBody());
    }
}
