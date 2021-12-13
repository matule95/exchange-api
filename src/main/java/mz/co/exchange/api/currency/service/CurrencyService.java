package mz.co.exchange.api.currency.service;

import mz.co.exchange.api.currency.domain.CreateCurrencyCommand;
import mz.co.exchange.api.currency.domain.UpdateCurrencyCommand;
import mz.co.exchange.api.currency.presentation.CurrencyJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CurrencyService {
    Page<CurrencyJson> findAll(Pageable pageable);
    CurrencyJson create(CreateCurrencyCommand command);
    CurrencyJson update(UpdateCurrencyCommand command, Long id);
    void deleteById(Long id);
    CurrencyJson getById(Long id);
}
