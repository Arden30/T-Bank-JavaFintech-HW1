package arden.java.currencyservice.clients;

import arden.java.currencyservice.api.dto.response.CurrencyRateResponse;
import arden.java.currencyservice.api.dto.response.CurrencyRateResponse.CurrencyRateWithIDResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CurrencyRateWebClient {
    private final WebClient webClient;
    private final XmlMapper xmlMapper;

    public Optional<CurrencyRateResponse> currencyRate(String currencyCode) {
        return webClient.get()
                .header("date_req", LocalDate.now().toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(resp -> {
                    try {
                        List<CurrencyRateWithIDResponse> currencyRateWithIDResponse = xmlMapper.readValue(resp, new TypeReference<>() {
                        });

                        return Mono.just(currencyRateWithIDResponse.stream().filter(r -> r.id() != null && r.id().equals(currencyCode))
                                .map(r -> CurrencyRateResponse.builder()
                                        .rate(Double.valueOf(r.value().replace(",", ".")))
                                        .currency(r.charCode())
                                        .build())
                                .findFirst());
                    } catch (JsonProcessingException e) {
                        log.error(e.getMessage());
                        return Mono.empty();
                    }
                }).block();
    }
}
