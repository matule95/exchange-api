package mz.co.exchange.api.rate.presentation;

import lombok.Data;
import mz.co.exchange.api.currency.domain.Currency;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class RateJson {
    private Long id;
    private String baseCurrency;
    private String targetCurrency;
    private Float purchase;
    private Float sale;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
