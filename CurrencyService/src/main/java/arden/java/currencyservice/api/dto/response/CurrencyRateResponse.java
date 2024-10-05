package arden.java.currencyservice.api.dto.response;

public record CurrencyRateResponse(
        String currency,
        Double rate
) {
}
