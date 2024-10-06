package arden.java.currencyservice.service;

import arden.java.currencyservice.api.dto.request.CurrencyConvertRequest;
import arden.java.currencyservice.api.dto.response.CurrencyConvertResponse;
import arden.java.currencyservice.api.dto.response.CurrencyRateResponse;
import arden.java.currencyservice.api.dto.response.CurrencyRateResponse.CurrencyRateWithIDResponse;
import arden.java.currencyservice.clients.CurrencyWebClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyWebClient currencyRateWebClient;
    private final XmlMapper xmlMapper;

    @Override
    public CurrencyRateResponse getCurrencyRate(String code) {
        return readAndParseCurrencyRate().stream()
                .filter(currency -> currency.id() != null && currency.id().equals(code))
                .map(currency -> CurrencyRateResponse.builder()
                        .rate(Double.valueOf(currency.value().replace(",", ".")))
                        .currency(currency.charCode())
                        .build())
                .findFirst().orElseThrow(RuntimeException::new);
    }

    @Override
    public CurrencyConvertResponse convertCurrency(CurrencyConvertRequest currencyConvertRequest) {
        return readAndParseCurrencyRate().stream()
                .filter(currency -> currency.id() != null && (currency.charCode().equals(currencyConvertRequest.fromCurrency()) || currency.charCode().equals(currencyConvertRequest.toCurrency())))
                .map(currency -> Double.parseDouble(currency.value().replace(",", ".")) / currency.nominal())
                .reduce((from, to) -> from / to * Double.parseDouble(currencyConvertRequest.amount()))
                .map(convertedAmount -> CurrencyConvertResponse.builder()
                        .fromCurrency(currencyConvertRequest.fromCurrency())
                        .toCurrency(currencyConvertRequest.toCurrency())
                        .convertedAmount(convertedAmount)
                        .build())
                .orElseThrow(RuntimeException::new);
    }

    private List<CurrencyRateWithIDResponse> readAndParseCurrencyRate() {
        Optional<String> currencyRateResponse = currencyRateWebClient.currencyRate();
        try {
            if (currencyRateResponse.isPresent()) {
                return xmlMapper.readValue(currencyRateResponse.get(), new TypeReference<>() {
                });
            } else {
                log.error("Currency rate not found");
                throw new RuntimeException("Currency rate not found");
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException();
        }
    }
}
