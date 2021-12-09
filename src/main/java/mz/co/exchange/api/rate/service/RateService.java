package mz.co.exchange.api.rate.service;

import mz.co.exchange.api.rate.domain.CreateRateCommand;
import mz.co.exchange.api.rate.presentation.RateJson;

public interface RateService {
    RateJson create(CreateRateCommand command);
}
