package mz.co.exchange.api.history.domain.query;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class HistoryQuery {
    private LocalDate startDate;
    private LocalDate endDate;
}
