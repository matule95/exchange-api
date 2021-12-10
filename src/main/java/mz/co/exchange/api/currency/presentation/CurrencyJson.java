package mz.co.exchange.api.currency.presentation;

import lombok.Data;
import mz.co.exchange.api.provider.domain.Provider;

import java.time.LocalDateTime;

@Data
public class CurrencyJson {
    private Long id;
    private String name;
    private String isoCode;
    private String provider;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
