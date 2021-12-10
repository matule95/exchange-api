package mz.co.exchange.api.currency.domain;

import lombok.Data;

@Data
public class CreateCurrencyCommand {
    private String isoCode;
    private String name;
}
