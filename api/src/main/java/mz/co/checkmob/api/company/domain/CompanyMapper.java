package mz.co.checkmob.api.company.domain;

import mz.co.checkmob.api.company.presentation.CompanyJson;
import mz.co.checkmob.api.user.domain.CreateUserCommand;
import mz.co.checkmob.api.user.domain.UpdateUserCommand;
import mz.co.checkmob.api.user.domain.User;
import mz.co.checkmob.api.user.domain.UserMapper;
import mz.co.checkmob.api.user.presentation.UserJson;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class CompanyMapper {
    public static CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Named("commandToModel")
    public abstract Company mapToModel(CreateCompanyCommand command);

    @InheritConfiguration
    public abstract Company mapToModel(UpdateCompanyCommand command);

    public abstract void updateModel(@MappingTarget Company company, UpdateCompanyCommand command);

    public abstract void cloneModel(@MappingTarget Company company, Company companySource);

    @InheritInverseConfiguration
    public abstract CompanyJson mapToJson(Company company);
    public abstract List<CompanyJson> mapToJson(List<Company> companies);

    public Page<CompanyJson> mapToJson(Page<Company> companies) {
        return new PageImpl<>(mapToJson(companies.getContent()), companies.getPageable(), companies.getTotalElements());
    }
}
