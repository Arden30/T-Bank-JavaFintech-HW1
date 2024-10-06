package arden.java.currencyservice.api.dto.response;

import lombok.Builder;

@Builder
public record CurrencyConvertResponse(
        String fromCurrency,
        String toCurrency,
        Double convertedAmount
) {
}
