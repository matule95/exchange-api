package mz.co.exchange.api.provider.domain;

import mz.co.exchange.api.provider.presentation.ProviderJson;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class ProviderMapper {
    public static ProviderMapper INSTANCE = Mappers.getMapper(ProviderMapper.class);

    @Named("commandToModel")
    public abstract Provider mapToModel(CreateProviderCommand command);

    @InheritConfiguration
    public abstract Provider mapToModel(UpdateProviderCommand command);

    public abstract void updateModel(@MappingTarget Provider provider, UpdateProviderCommand command);

    public abstract void cloneModel(@MappingTarget Provider provider, Provider providerSource);

    @InheritInverseConfiguration
    public abstract ProviderJson mapToJson(Provider provider);
    public abstract List<ProviderJson> mapToJson(List<Provider> companies);

    public Page<ProviderJson> mapToJson(Page<Provider> companies) {
        return new PageImpl<>(mapToJson(companies.getContent()), companies.getPageable(), companies.getTotalElements());
    }
}
