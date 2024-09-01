package arden.java.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class City {
    @JsonProperty("slug")
    private String slug;

    @JsonProperty("coords")
    private Coordinates coords;
}
