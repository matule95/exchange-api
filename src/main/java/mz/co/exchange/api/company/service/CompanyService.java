package mz.co.exchange.api.company.service;

import mz.co.exchange.api.company.domain.CompanyStatus;
import mz.co.exchange.api.company.domain.CreateCompanyCommand;
import mz.co.exchange.api.company.domain.UpdateCompanyCommand;
import mz.co.exchange.api.company.domain.query.CompanyQuery;
import mz.co.exchange.api.company.presentation.CompanyJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService {
    CompanyJson create(CreateCompanyCommand createCompanyCommand);
    CompanyJson fetchCompany(Long companyId);
    Page<CompanyJson> fetchCompanies(Pageable pageable, CompanyQuery companyQuery);
    CompanyJson update(UpdateCompanyCommand updateCompanyCommand, Long companyId);
    CompanyJson setStatus (Long companyId, CompanyStatus companyStatus);
    void deleteById(Long companyId);
    long countAllCompanies();
}