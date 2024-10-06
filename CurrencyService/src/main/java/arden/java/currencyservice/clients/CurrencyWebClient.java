package arden.java.currencyservice.clients;

import arden.java.currencyservice.exception.CurrencyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class CurrencyWebClient {
    private final WebClient webClient;

    public String currencyRate() {
        return webClient.get()
                .header("date_req", LocalDate.now().toString())
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(WebClientRequestException.class, e -> {
                    throw new CurrencyException("Сервис не доступен", HttpStatus.SERVICE_UNAVAILABLE).withHeader("Retry-After:", "3600");
                })
                .log("Получен ответ от сервера")
                .block();
    }
}
