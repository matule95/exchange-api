package mz.co.exchange.api;

import com.github.javafaker.Faker;
import mz.co.exchange.api.currency.domain.Currency;
import mz.co.exchange.api.provider.domain.Provider;
import mz.co.exchange.api.rate.domain.CreateRateCommand;
import mz.co.exchange.api.rate.domain.Rate;
import mz.co.exchange.api.rate.domain.UpdateRateCommand;

public class TestUtils {
    private final Faker faker = new Faker();

    protected final Faker faker() {
        return faker;
    }


    // DTOS
    protected CreateRateCommand getCreateRateCommand(){
        CreateRateCommand command = new CreateRateCommand();
        command.setBaseCurrencyId(faker().number().randomNumber());
        command.setTargetCurrencyId(faker().number().randomNumber());
        command.setPurchase((float) faker().number().randomDouble(3,1000,10000));
        command.setSale((float) faker().number().randomDouble(3,1000,10000));
        return command;
    }
    protected UpdateRateCommand getUpdateRateCommand(){
        UpdateRateCommand command = new UpdateRateCommand();
        command.setPurchase((float) faker().number().randomDouble(3,1000,10000));
        command.setSale((float) faker().number().randomDouble(3,1000,10000));
        return command;
    }

    // Domains
    protected Rate getAnyRate(){
        Rate rate = new Rate();
        rate.setId(faker().number().randomNumber());
        rate.setBaseCurrency(getAnyCurrency());
        rate.setTargetCurrency(getAnyCurrency());
        rate.setPurchase((float) faker().number().randomDouble(3,1000,10000));
        rate.setSale((float) faker().number().randomDouble(3,1000,10000));
        return rate;
    }
    protected Currency getAnyCurrency(){
        Currency currency = new Currency();
        currency.setId(faker().random().nextLong());
        currency.setName(faker().currency().name());
        currency.setIsoCode(faker().currency().code());
        return currency;
    }

    protected Provider getAnyProvider(){
        Provider provider = new Provider();
        provider.setId(faker().random().nextLong());
        provider.setEmail(faker().internet().emailAddress());
        provider.setName(faker().name().fullName());
        return provider;
    }

    // JSONS
}
