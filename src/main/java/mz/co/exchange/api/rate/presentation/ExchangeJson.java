package mz.co.exchange.api.rate.presentation;

import lombok.Data;

import java.util.List;

@Data
public class ExchangeJson {
    private String result;
    private String provider;
    private String dateTime;
    private String baseCurrency;
    private List<CurrencyRateJson> rates;
}
