package mz.co.exchange.api.provider.service;

import lombok.RequiredArgsConstructor;
import mz.co.exchange.api.provider.domain.*;
import mz.co.exchange.api.provider.domain.query.ProviderQuery;
import mz.co.exchange.api.provider.domain.query.ProviderQueryGateway;
import mz.co.exchange.api.provider.persistence.ProviderRepository;
import mz.co.exchange.api.provider.presentation.ProviderJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository providerRepository;
    private final ProviderQueryGateway providerQueryGateway;

    @Override
    public Page<ProviderJson> fetchProviders(Pageable pageable, ProviderQuery providerQuery) {
        return providerQueryGateway.executeQuery(pageable, providerQuery);
    }

    @Override
    public ProviderJson create(CreateProviderCommand createProviderCommand) {
        Provider provider = ProviderMapper.INSTANCE.mapToModel(createProviderCommand);
        return ProviderMapper.INSTANCE.mapToJson(providerRepository.save(provider));
    }

    @Override
    public ProviderJson fetchProvider(Long providerId) {
        Provider provider = findById(providerId);
        return ProviderMapper.INSTANCE.mapToJson(provider);
    }

    @Override
    public ProviderJson update(UpdateProviderCommand updateProviderCommand, Long providerId) {
        Provider provider = findById(providerId);
        ProviderMapper.INSTANCE.updateModel(provider, updateProviderCommand);
        return ProviderMapper.INSTANCE.mapToJson(providerRepository.save(provider));
    }


    @Override
    public void deleteById(Long providerId) {
        providerRepository.deleteById(providerId);
    }

    public Provider findById(Long id){
        Provider provider = providerRepository.findById(id).get();
        if(provider == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return provider;
    }
}
