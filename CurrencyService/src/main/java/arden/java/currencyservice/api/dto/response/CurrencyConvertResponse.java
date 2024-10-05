package arden.java.currencyservice.api.dto.response;

public record CurrencyConvertResponse(
        String fromCurrency,
        String toCurrency,
        Double convertedAmount
) {
}
