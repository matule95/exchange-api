package mz.co.exchange.api.history.presentation;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistoryJson {
    private String baseCurrency;
    private String targetCurrency;
    private Float sale;
    private Float purchase;
    private LocalDateTime date;
}
