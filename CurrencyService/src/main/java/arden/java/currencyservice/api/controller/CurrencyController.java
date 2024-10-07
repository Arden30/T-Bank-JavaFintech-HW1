package arden.java.currencyservice.api.controller;

import arden.java.currencyservice.api.dto.request.CurrencyConvertRequest;
import arden.java.currencyservice.api.dto.response.CurrencyConvertResponse;
import arden.java.currencyservice.api.dto.response.CurrencyRateResponse;
import arden.java.currencyservice.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@RestController
@RequestMapping("/api/v1/currencies")
@RequiredArgsConstructor
@Tag(name="Контроллер валют", description="Делает запросы к API ЦБ для получения информации по курсу валюты или осуществляет конвертацию денежной суммы из одной валюты в другую")
public class CurrencyController {
    private final CurrencyService currencyService;

    @Operation(
            summary = "Получение информации по валюте",
            description = "Позволяет узнать курс валюты по отношению к российскому рублю"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Курс найден",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CurrencyRateResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Валюты не существует",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Валюта не найдена в базе ЦБ",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Сервер не доступен",
                    content = @Content)})
    @GetMapping("/rates/{code}")
    public ResponseEntity<CurrencyRateResponse> rate(@PathVariable @Parameter(description = "Идентификатор валюты") String code) {
        return ResponseEntity.ok(currencyService.getCurrencyRate(code));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Курс найден",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CurrencyConvertResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Валюты не существует/Стоимость не может быть отрицательной",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Валюта не найдена в базе ЦБ",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Сервер не доступен",
                    content = @Content)})
    @PostMapping("/convert")
    public ResponseEntity<CurrencyConvertResponse> convert(@RequestBody @Valid CurrencyConvertRequest request) {
        return ResponseEntity.ok(currencyService.convertCurrency(request));
    }
}
