package arden.java.currencyservice.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CurrencyConvertRequest(
        @NotBlank(message = "Исходная валюта для конвертации не указана")
        String fromCurrency,
        @NotBlank(message = "Целевая валюта для конвертации не указана")
        String toCurrency,
        @Min(value = 0, message = "Количество валюты для конвертации должно быть неотрицательным числом")
        Double amount
) {
}
