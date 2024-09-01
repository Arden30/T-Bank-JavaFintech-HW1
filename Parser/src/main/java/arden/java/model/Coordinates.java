package arden.java.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Coordinates {
    @JsonProperty("lat")
    private double lat;

    @JsonProperty("lon")
    private double lon;
}
