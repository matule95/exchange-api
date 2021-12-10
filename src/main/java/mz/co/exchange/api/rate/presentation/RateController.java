package mz.co.exchange.api.rate.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mz.co.exchange.api.currency.domain.CreateCurrencyCommand;
import mz.co.exchange.api.currency.presentation.CurrencyJson;
import mz.co.exchange.api.history.domain.query.HistoryQuery;
import mz.co.exchange.api.history.domain.query.HistoryQueryGateway;
import mz.co.exchange.api.history.presentation.HistoryJson;
import mz.co.exchange.api.history.service.HistoryService;
import mz.co.exchange.api.rate.domain.CreateRateCommand;
import mz.co.exchange.api.rate.domain.UpdateRateCommand;
import mz.co.exchange.api.rate.service.RateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = "Rate Management")
@RequestMapping(path = "/api/v1/rates", name = "rate")
@RequiredArgsConstructor
public class RateController {
    private final RateService service;
    private final HistoryService historyService;

    @PostMapping
    @ApiOperation("Create a new rate")
    public ResponseEntity<RateJson> createRate(@RequestBody @Valid CreateRateCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(command));
    }

    @PatchMapping("/{id}")
    @ApiOperation("Update rate values")
    public ResponseEntity<RateJson> updateDailyRate(@PathVariable Long id, @RequestBody @Valid UpdateRateCommand command) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(command, id));
    }

    @GetMapping("/{baseCurrencyId}/exchange")
    @ApiOperation("Currency Rate Consult")
    public ResponseEntity<ExchangeJson> getCurrencyRates(@PathVariable Long baseCurrencyId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getBaseCurrencyRates(baseCurrencyId));
    }

    @GetMapping("/{rateId}/history")
    @ApiOperation("Currency Rate Consult")
    public ResponseEntity<Page<HistoryJson>> getRateHistory(@PageableDefault Pageable pageable, @PathVariable Long rateId, HistoryQuery historyQuery) {
        return ResponseEntity.status(HttpStatus.OK).body(historyService.getRateHistory(pageable, historyQuery, rateId));
    }
}
