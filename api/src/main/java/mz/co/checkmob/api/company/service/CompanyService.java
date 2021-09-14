package mz.co.checkmob.api.company.service;

import mz.co.checkmob.api.company.domain.CreateCompanyCommand;
import mz.co.checkmob.api.company.domain.UpdateCompanyCommand;
import mz.co.checkmob.api.company.domain.query.CompanyQuery;
import mz.co.checkmob.api.company.presentation.CompanyJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService {
    CompanyJson create(CreateCompanyCommand createCompanyCommand);
    CompanyJson fetchCompany(Long companyId);
    Page<CompanyJson> fetchCompanies(Pageable pageable, CompanyQuery companyQuery);
    CompanyJson update(UpdateCompanyCommand updateCompanyCommand, Long companyId);
    void deleteById(Long companyId);
}
