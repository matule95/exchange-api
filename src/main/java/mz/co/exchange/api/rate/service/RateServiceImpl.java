package mz.co.exchange.api.rate.service;

import lombok.RequiredArgsConstructor;
import mz.co.exchange.api.currency.domain.Currency;
import mz.co.exchange.api.currency.service.CurrencyService;
import mz.co.exchange.api.currency.service.CurrencyServiceImpl;
import mz.co.exchange.api.rate.domain.CreateRateCommand;
import mz.co.exchange.api.rate.domain.Rate;
import mz.co.exchange.api.rate.domain.RateMapper;
import mz.co.exchange.api.rate.domain.UpdateRateCommand;
import mz.co.exchange.api.rate.persistence.RateRepository;
import mz.co.exchange.api.rate.presentation.CurrencyRateJson;
import mz.co.exchange.api.rate.presentation.ExchangeJson;
import mz.co.exchange.api.rate.presentation.RateJson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService{
    private final CurrencyServiceImpl currencyService;
    private final RateRepository repository;
    private final RateMapper MAPPER = RateMapper.INSTANCE;
    @Override
    public RateJson create(CreateRateCommand command) {
        findByBaseCurrencyAndTargetCurrency(command.getBaseCurrencyId(), command.getTargetCurrencyId());
        Rate rate = MAPPER.mapToModel(command);
        rate.setBaseCurrency(currencyService.findById(command.getBaseCurrencyId()));
        rate.setTargetCurrency(currencyService.findById(command.getTargetCurrencyId()));
        return MAPPER.mapToJson(repository.save(rate));
    }

    @Override
    public ExchangeJson getBaseCurrencyRates(Long baseCurrencyId) {
        ExchangeJson exchangeJson = new ExchangeJson();
        Currency currency = currencyService.findById(baseCurrencyId);
        exchangeJson.setDateTime(LocalDateTime.now());
        exchangeJson.setBaseCurrency(currency.getIsoCode());
        exchangeJson.setResult("success");
        exchangeJson.setRates(MAPPER.mapToCurrencyJson(repository.findByBaseCurrencyId(baseCurrencyId)));
        return exchangeJson;
    }

    public Rate findByBaseCurrencyAndTargetCurrency(Long baseCurrencyId,Long targetCurrencyId){
        Rate rate = repository.findByBaseCurrencyIdAndTargetCurrencyId(baseCurrencyId,targetCurrencyId);
        if(rate != null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return rate;
    }

    @Override
    public RateJson update(UpdateRateCommand command, Long id) {
        Rate rate = findById(id);
        MAPPER.updateModel(rate,command);
        return MAPPER.mapToJson(repository.save(rate));
    }

    public Rate findById(Long id){
        Rate rate = repository.findById(id).get();
        if(rate == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return rate;
    }

}
