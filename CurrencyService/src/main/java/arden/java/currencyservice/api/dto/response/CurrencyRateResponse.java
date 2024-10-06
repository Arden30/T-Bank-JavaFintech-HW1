package arden.java.currencyservice.api.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Builder;

@Builder
public record CurrencyRateResponse(
        String currency,
        Double rate
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record CurrencyRateWithIDResponse(
            @JacksonXmlProperty(isAttribute = true, localName = "ID")
            String id,

            @JacksonXmlProperty(localName = "CharCode")
            String charCode,

            @JacksonXmlProperty(localName = "Value")
            String value,

            @JacksonXmlProperty(localName = "Nominal")
            Integer nominal
    ) {
    }
}
