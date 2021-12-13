package mz.co.exchange.api.provider.presentation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mz.co.exchange.api.provider.domain.CreateProviderCommand;
import mz.co.exchange.api.provider.domain.UpdateProviderCommand;
import mz.co.exchange.api.provider.domain.query.ProviderQuery;
import mz.co.exchange.api.provider.service.ProviderServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = "Provider Management")
@RequestMapping(path = "/api/v1/providers", name = "providers")
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderServiceImpl providerService;


    @PostMapping
    @ApiOperation("Create a new provider")
    public ResponseEntity<ProviderJson> createCompany(@RequestBody @Valid CreateProviderCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(providerService.create(command));
    }

    @GetMapping("/{id}")
    @ApiOperation("Fetch Provider By Id")
    public ResponseEntity<ProviderJson> getProviderById(@PathVariable Long id) {
            return ResponseEntity.ok(providerService.fetchProvider(id));
    }

    @GetMapping
    @ApiOperation("Fetch All Providers")
    public ResponseEntity<Page<ProviderJson>> getProviders(ProviderQuery providerQuery, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(providerService.fetchProviders(pageable, providerQuery));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update Provider Details")
    public ResponseEntity<ProviderJson> updateProvider(@PathVariable Long id, @RequestBody @Valid UpdateProviderCommand command) {
        return  ResponseEntity.ok(providerService.update(command,id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Provider")
    public ResponseEntity<?> deleteProvider(@PathVariable Long id) {
            providerService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
    }
}
