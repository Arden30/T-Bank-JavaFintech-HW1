package arden.java.currencyservice.clients;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CurrencyWebClient {
    private final WebClient webClient;

    public Optional<String> currencyRate() {
        return webClient.get()
                .header("date_req", LocalDate.now().toString())
                .retrieve()
                .bodyToMono(String.class)
                .blockOptional();
    }
}
