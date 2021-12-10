package mz.co.exchange.api.rate.presentation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CurrencyRateJson {
    private String currency;
    private Float purchase;
    private Float sale;
}
