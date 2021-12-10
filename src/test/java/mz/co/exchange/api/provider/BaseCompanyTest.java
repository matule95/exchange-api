package mz.co.exchange.api.provider;

import mz.co.exchange.api.provider.domain.CreateProviderCommand;
import mz.co.exchange.api.provider.domain.Provider;
import mz.co.exchange.api.provider.domain.ProviderMapper;
import mz.co.exchange.api.provider.domain.UpdateProviderCommand;
import mz.co.exchange.api.provider.presentation.ProviderJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class BaseCompanyTest {
    protected Provider getAnyCompany(){
        return ProviderMapper.INSTANCE.mapToModel(getCreateCompanyCommand());
    }
    protected CreateProviderCommand getCreateCompanyCommand(){
        CreateProviderCommand command = new CreateProviderCommand();
        command.setName("Awesome Provider");
        command.setEmail("aws@gmail.com");
        return command;
    }
    protected Page<ProviderJson> getAnyCompanyPage(){
        List<Provider> companies = new ArrayList<>();
        companies.add(getAnyCompany());
        companies.add(getAnyCompany());
        companies.add(getAnyCompany());
        companies.add(getAnyCompany());
        return ProviderMapper.INSTANCE.mapToJson(new PageImpl<>(companies));
    }
    protected UpdateProviderCommand getAnyUpdateCompanyCommand(){
        UpdateProviderCommand command = new UpdateProviderCommand();
        command.setId(1L);
        command.setName("Mr. Awesome");
        command.setEmail("helio@gmail.com");
        return command;
    }
}
