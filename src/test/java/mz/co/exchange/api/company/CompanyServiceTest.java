package mz.co.exchange.api.company;

import mz.co.exchange.api.company.domain.Company;
import mz.co.exchange.api.company.domain.query.CompanyQuery;
import mz.co.exchange.api.company.domain.query.CompanyQueryGateway;
import mz.co.exchange.api.company.persistence.CompanyRepository;
import mz.co.exchange.api.company.presentation.CompanyJson;
import mz.co.exchange.api.company.service.CompanyService;
import mz.co.exchange.api.company.service.CompanyServiceImpl;
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
public class CompanyServiceTest extends BaseCompanyTest{
    @Autowired
    private CompanyService service;
    @Mock
    private CompanyRepository repository;
    @Mock
    private CompanyQueryGateway companyQueryGateway;
    @Captor
    private ArgumentCaptor<Company> captor;

    public CompanyServiceTest() {
    }

    @BeforeEach
    void initializer(){
        service = new CompanyServiceImpl(repository,companyQueryGateway);
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
        CompanyQuery companyQuery = new CompanyQuery();
        Pageable pageable = Pageable.unpaged();
        Mockito.when(companyQueryGateway.executeQuery(pageable,companyQuery)).thenReturn(getAnyCompanyPage());
        Page<CompanyJson> companies = service.fetchCompanies(pageable,companyQuery);
        Mockito.verify(companyQueryGateway).executeQuery(pageable,companyQuery);
        Assertions.assertEquals(companies.getTotalElements(),getAnyCompanyPage().getTotalElements());
    }
    @Test
    void shouldGetCompanyById(){
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(getAnyCompany()));
        CompanyJson companyJson = service.fetchCompany(1L);
        Mockito.verify(repository).findById(1L);
        Assertions.assertNotNull(companyJson);
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
