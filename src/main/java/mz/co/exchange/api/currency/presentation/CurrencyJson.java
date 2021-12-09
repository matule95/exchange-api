package mz.co.exchange.api.currency.presentation;

import lombok.Data;
import mz.co.exchange.api.company.domain.CompanyStatus;

import java.time.LocalDateTime;

@Data
public class CurrencyJson {
    private Long id;
    private String name;
    private String abbreviation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
