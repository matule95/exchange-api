package mz.co.exchange.api.company.service;

import lombok.RequiredArgsConstructor;
import mz.co.exchange.api.company.domain.*;
import mz.co.exchange.api.company.domain.query.CompanyQuery;
import mz.co.exchange.api.company.domain.query.CompanyQueryGateway;
import mz.co.exchange.api.company.persistence.CompanyRepository;
import mz.co.exchange.api.company.presentation.CompanyJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Clock;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyQueryGateway companyQueryGateway;

    @Override
    public Page<CompanyJson> fetchCompanies(Pageable pageable, CompanyQuery companyQuery) {
        return companyQueryGateway.executeQuery(pageable,companyQuery);
    }

    @Override
    public CompanyJson create(CreateCompanyCommand createCompanyCommand) {
        Company company = CompanyMapper.INSTANCE.mapToModel(createCompanyCommand);
        return CompanyMapper.INSTANCE.mapToJson(companyRepository.save(company));
    }

    @Override
    public CompanyJson fetchCompany(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(EntityNotFoundException::new);
        return CompanyMapper.INSTANCE.mapToJson(company);
    }

    @Override
    public CompanyJson update(UpdateCompanyCommand updateCompanyCommand, Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(EntityNotFoundException::new);
        CompanyMapper.INSTANCE.updateModel(company,updateCompanyCommand);
        return CompanyMapper.INSTANCE.mapToJson(companyRepository.save(company));
    }

@Override
    public  CompanyJson setStatus(Long companyId, CompanyStatus status){
        Company company = companyRepository.findById(companyId).orElseThrow(EntityNotFoundException::new);
        company.setCompanyStatus(status);
        return CompanyMapper.INSTANCE.mapToJson(companyRepository.save(company));
    }

    @Override
    public void deleteById(Long companyId) {
        companyRepository.deleteById(companyId);
    }

    @Override
    public long countAllCompanies() {
        return companyRepository.count();
    }
}
