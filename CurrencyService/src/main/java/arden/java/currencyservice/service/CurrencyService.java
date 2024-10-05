package arden.java.currencyservice.service;

import arden.java.currencyservice.api.dto.response.CurrencyRateResponse;

public interface CurrencyService {
    CurrencyRateResponse getCurrencyRate(String code);
}
