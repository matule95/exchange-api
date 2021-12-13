package mz.co.exchange.api.rate;

import mz.co.exchange.api.TestUtils;
import mz.co.exchange.api.currency.service.CurrencyServiceImpl;
import mz.co.exchange.api.history.domain.History;
import mz.co.exchange.api.history.persistence.HistoryRepository;
import mz.co.exchange.api.history.service.HistoryService;
import mz.co.exchange.api.rate.domain.CreateRateCommand;
import mz.co.exchange.api.rate.domain.Rate;
import mz.co.exchange.api.rate.domain.RateMapper;
import mz.co.exchange.api.rate.persistence.RateRepository;
import mz.co.exchange.api.rate.presentation.ExchangeJson;
import mz.co.exchange.api.rate.presentation.RateController;
import mz.co.exchange.api.rate.presentation.RateJson;
import mz.co.exchange.api.rate.service.RateService;
import mz.co.exchange.api.rate.service.RateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class RateServiceTests extends TestUtils {
    private RateService underTest;
    @Mock
    private CurrencyServiceImpl currencyService;
    @Mock
    private RateRepository repository;
    @Mock
    private HistoryService historyService;

    @BeforeEach
    void setUp() {
        underTest = new RateServiceImpl(currencyService, repository, historyService);
    }

    @Test
    void shouldNotCreateRate() {
        Rate rate = getAnyRate();
        Long currencyId2 = faker().number().numberBetween(1L,5L);
        Long currencyId = faker().number().numberBetween(6L,15L);
        Mockito.when(repository.findByBaseCurrencyIdAndTargetCurrencyId(currencyId, currencyId2)).thenReturn(rate);
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            underTest.create(getCreateRateCommand());
        });
        Mockito.verify(repository,Mockito.atLeastOnce()).findByBaseCurrencyIdAndTargetCurrencyId(currencyId, currencyId2);
    }


    @Test
    void shouldCreateRate() {
        Mockito.when(historyService.addHistory(Mockito.any())).thenReturn(true);
        Mockito.when(repository.save(Mockito.any())).thenReturn(getAnyRate());
        underTest.create(getCreateRateCommand());
        Mockito.verify(historyService).addHistory(Mockito.any());
        Mockito.verify(repository).save(Mockito.any());
    }

}
