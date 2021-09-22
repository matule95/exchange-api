package mz.co.checkmob.api.company;

import mz.co.checkmob.api.company.domain.Company;
import mz.co.checkmob.api.company.domain.CompanyMapper;
import mz.co.checkmob.api.company.domain.CreateCompanyCommand;
import mz.co.checkmob.api.company.domain.UpdateCompanyCommand;
import mz.co.checkmob.api.company.presentation.CompanyJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BaseCompanyTest {
    protected Company getAnyCompany(){
        return CompanyMapper.INSTANCE.mapToModel(getCreateCompanyCommand());
    }
    protected CreateCompanyCommand getCreateCompanyCommand(){
        CreateCompanyCommand command = new CreateCompanyCommand();
        command.setName("Awesome Company");
        command.setEmail("aws@gmail.com");
        return command;
    }
    protected Page<CompanyJson> getAnyCompanyPage(){
        List<Company> companies = new ArrayList<>();
        companies.add(getAnyCompany());
        companies.add(getAnyCompany());
        companies.add(getAnyCompany());
        companies.add(getAnyCompany());
        return CompanyMapper.INSTANCE.mapToJson(new PageImpl<>(companies));
    }
    protected UpdateCompanyCommand getAnyUpdateCompanyCommand(){
        UpdateCompanyCommand command = new UpdateCompanyCommand();
        command.setId(1L);
        command.setName("Mr. Awesome");
        command.setEmail("helio@gmail.com");
        return command;
    }
}
