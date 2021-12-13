package mz.co.exchange.api.rate.service;

import mz.co.exchange.api.rate.domain.CreateRateCommand;
import mz.co.exchange.api.rate.domain.Rate;
import mz.co.exchange.api.rate.domain.UpdateRateCommand;
import mz.co.exchange.api.rate.presentation.ExchangeJson;
import mz.co.exchange.api.rate.presentation.RateJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;


public interface RateService {
    RateJson create(CreateRateCommand command);
    ExchangeJson getBaseCurrencyRates(Long baseCurrencyId);
    RateJson update(UpdateRateCommand command, Long id);
    RateJson fetchRate(Long rateId);
    Page<RateJson> fetchRates(Pageable pageable);
}
