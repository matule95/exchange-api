package mz.co.exchange.api.rate.presentation;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExchangeJson {
    private String result;
    private String provider;
    private LocalDateTime dateTime;
    private String baseCurrency;
    private List<CurrencyRateJson> rates;
}
