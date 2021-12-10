package mz.co.exchange.api.currency.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mz.co.exchange.api.currency.domain.CreateCurrencyCommand;
import mz.co.exchange.api.currency.domain.UpdateCurrencyCommand;
import mz.co.exchange.api.currency.service.CurrencyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = "Currency Management")
@RequestMapping(path = "/api/v1/currencies", name = "currencies")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @PostMapping
    @ApiOperation("Create a new currency")
    public ResponseEntity<CurrencyJson> createCurrency(@RequestBody @Valid CreateCurrencyCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(currencyService.create(command));
    }

    @GetMapping("/{id}")
    @ApiOperation("Fetch Currency By Id")
    public ResponseEntity<CurrencyJson> getCurrencyById(@PathVariable Long id) {
            return ResponseEntity.ok(currencyService.getById(id));
    }

    @GetMapping
    @ApiOperation("Fetch All Currencies")
    public ResponseEntity<Page<CurrencyJson>> getCurrencies(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(currencyService.findAll(pageable));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update Currency Details")
    public ResponseEntity<CurrencyJson> updateCurrency(@PathVariable Long id,@RequestBody @Valid UpdateCurrencyCommand command) {
        return  ResponseEntity.ok(currencyService.update(command,id));
    }


    @DeleteMapping("/{id}")
    @ApiOperation("Delete Currency")
    public ResponseEntity<?> deleteCurrency(@PathVariable Long id) {
            currencyService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
    }
}
