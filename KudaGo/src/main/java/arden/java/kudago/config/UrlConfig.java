package arden.java.kudago.config;

import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "base-config")
public record UrlConfig(
        @NonNull
        String url
) {
}
