package mz.co.exchange.api.currency.domain;

import lombok.Data;

@Data
public class UpdateCurrencyCommand {
    private String abbreviation;
    private String name;
}
