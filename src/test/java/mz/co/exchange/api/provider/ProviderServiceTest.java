package mz.co.exchange.api.provider;

import mz.co.exchange.api.provider.domain.Provider;
import mz.co.exchange.api.provider.domain.query.ProviderQuery;
import mz.co.exchange.api.provider.domain.query.ProviderQueryGateway;
import mz.co.exchange.api.provider.persistence.ProviderRepository;
import mz.co.exchange.api.provider.presentation.ProviderJson;
import mz.co.exchange.api.provider.service.ProviderService;
import mz.co.exchange.api.provider.service.ProviderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProviderServiceTest extends BaseCompanyTest{
    @Autowired
    private ProviderService service;
    @Mock
    private ProviderRepository repository;
    @Mock
    private ProviderQueryGateway providerQueryGateway;
    @Captor
    private ArgumentCaptor<Provider> captor;

    public ProviderServiceTest() {
    }

    @BeforeEach
    void initializer(){
        service = new ProviderServiceImpl(repository, providerQueryGateway);
    }
    @Test
    void shouldSaveCompany(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(getAnyCompany());
        service.create(getCreateCompanyCommand());
        Mockito.verify(repository).save(captor.capture());
        Assertions.assertNotNull(captor.getValue());
    }
    @Test
    void shouldListCompanies(){
        ProviderQuery providerQuery = new ProviderQuery();
        Pageable pageable = Pageable.unpaged();
        Mockito.when(providerQueryGateway.executeQuery(pageable, providerQuery)).thenReturn(getAnyCompanyPage());
        Page<ProviderJson> companies = service.fetchProviders(pageable, providerQuery);
        Mockito.verify(providerQueryGateway).executeQuery(pageable, providerQuery);
        Assertions.assertEquals(companies.getTotalElements(),getAnyCompanyPage().getTotalElements());
    }
    @Test
    void shouldGetCompanyById(){
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(getAnyCompany()));
        ProviderJson providerJson = service.fetchProvider(1L);
        Mockito.verify(repository).findById(1L);
        Assertions.assertNotNull(providerJson);
    }
    @Test
    void shouldUpdateCompany(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(getAnyCompany());
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(getAnyCompany()));
        service.update(getAnyUpdateCompanyCommand(),1L);
        Mockito.verify(repository).save(captor.capture());
        Assertions.assertNotNull(captor.getValue());
    }
}
