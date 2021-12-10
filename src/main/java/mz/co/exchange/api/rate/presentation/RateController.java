package mz.co.exchange.api.rate.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mz.co.exchange.api.currency.domain.CreateCurrencyCommand;
import mz.co.exchange.api.currency.presentation.CurrencyJson;
import mz.co.exchange.api.rate.domain.CreateRateCommand;
import mz.co.exchange.api.rate.domain.UpdateRateCommand;
import mz.co.exchange.api.rate.service.RateService;
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

    @PostMapping
    @ApiOperation("Create a new rate")
    public ResponseEntity<RateJson> createRate(@RequestBody @Valid CreateRateCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(command));
    }
    @PutMapping("/{id}")
    @ApiOperation("Update rate values")
    public ResponseEntity<RateJson> updateDailyRate(@PathVariable Long id,@RequestBody @Valid UpdateRateCommand command) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(command,id));
    }
    @GetMapping("/exchange/{baseCurrencyId}")
    @ApiOperation("Get currency exchange")
    public ResponseEntity<?> getCurrencyRates(@PathVariable Long baseCurrencyId){
        return ResponseEntity.status(HttpStatus.OK).body(service.getBaseCurrencyRates(baseCurrencyId));
    }
}
