package arden.java.currencyservice.api.dto.request;

public record CurrencyConvertRequest(
        String fromCurrency,
        String toCurrency,
        Double amount
) {
}
