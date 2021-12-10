package mz.co.exchange.api.currency.domain;

import mz.co.exchange.api.currency.presentation.CurrencyJson;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class CurrencyMapper {
    public static CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);

    @Named("commandToModel")
    public abstract Currency mapToModel(CreateCurrencyCommand command);

    @InheritConfiguration
    public abstract Currency mapToModel(UpdateCurrencyCommand command);

    public abstract void updateModel(@MappingTarget Currency currency, UpdateCurrencyCommand command);


    public abstract void cloneModel(@MappingTarget Currency currency, Currency currencySource);

    @InheritInverseConfiguration
    @Mapping(source = "provider.name",target = "provider")
    public abstract CurrencyJson mapToJson(Currency currency);
    public abstract List<CurrencyJson> mapToJson(List<Currency> currencies);

    public Page<CurrencyJson> mapToJson(Page<Currency> currencies) {
        return new PageImpl<>(mapToJson(currencies.getContent()), currencies.getPageable(), currencies.getTotalElements());
    }
}
