package mz.co.exchange.api.currency.domain;

import lombok.Data;

@Data
public class UpdateCurrencyCommand {
    private String isoCode;
    private String name;
}
