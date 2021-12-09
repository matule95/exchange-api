package mz.co.exchange.api.rate.domain;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class CreateRateCommand {
    private Long baseCurrencyId;
    private Long targetCurrencyId;
    @Positive
    private Float sale;
    @Positive
    private Float purchase;
}
