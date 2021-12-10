package mz.co.exchange.api.provider.service;

import mz.co.exchange.api.provider.domain.CreateProviderCommand;
import mz.co.exchange.api.provider.domain.UpdateProviderCommand;
import mz.co.exchange.api.provider.domain.query.ProviderQuery;
import mz.co.exchange.api.provider.presentation.ProviderJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProviderService {
    ProviderJson create(CreateProviderCommand createProviderCommand);
    ProviderJson fetchProvider(Long providerId);
    Page<ProviderJson> fetchProviders(Pageable pageable, ProviderQuery providerQuery);
    ProviderJson update(UpdateProviderCommand updateProviderCommand, Long providerId);
    void deleteById(Long providerId);
}
