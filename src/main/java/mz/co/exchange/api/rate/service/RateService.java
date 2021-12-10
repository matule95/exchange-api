package mz.co.exchange.api.rate.service;

import mz.co.exchange.api.rate.domain.CreateRateCommand;
import mz.co.exchange.api.rate.domain.Rate;
import mz.co.exchange.api.rate.domain.UpdateRateCommand;
import mz.co.exchange.api.rate.presentation.RateJson;

import java.util.List;

public interface RateService {
    RateJson create(CreateRateCommand command);
    List<Rate> getBaseCurrencyRates(Long baseCurrencyId);
    RateJson update(UpdateRateCommand command, Long id);
}
