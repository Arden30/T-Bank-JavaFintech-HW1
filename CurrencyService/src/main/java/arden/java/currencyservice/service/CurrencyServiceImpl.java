package arden.java.currencyservice.service;

import arden.java.currencyservice.api.dto.response.CurrencyRateResponse;
import arden.java.currencyservice.clients.CurrencyRateWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRateWebClient currencyRateWebClient;

    @Override
    public CurrencyRateResponse getCurrencyRate(String code) {
        Optional<CurrencyRateResponse> currencyRateResponse = currencyRateWebClient.currencyRate(code);

        return currencyRateResponse.orElse(null);

    }
}
