package mz.co.exchange.api.rate.domain;

import mz.co.exchange.api.currency.domain.CreateCurrencyCommand;
import mz.co.exchange.api.currency.domain.Currency;
import mz.co.exchange.api.currency.domain.UpdateCurrencyCommand;
import mz.co.exchange.api.currency.presentation.CurrencyJson;
import mz.co.exchange.api.rate.presentation.CurrencyRateJson;
import mz.co.exchange.api.rate.presentation.RateJson;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class RateMapper {
    public static RateMapper INSTANCE = Mappers.getMapper(RateMapper.class);

    @Named("commandToModel")
    public abstract Rate mapToModel(CreateRateCommand command);

    @InheritConfiguration
    public abstract Rate mapToModel(UpdateRateCommand command);

    public abstract void updateModel(@MappingTarget Rate rate, UpdateRateCommand command);


    public abstract void cloneModel(@MappingTarget Currency currency, Currency currencySource);

    @InheritInverseConfiguration
    @Mapping(source = "baseCurrency.isoCode",target = "baseCurrency")
    @Mapping(source = "targetCurrency.isoCode",target = "targetCurrency")
    public abstract RateJson mapToJson(Rate currency);

    @Mapping(source = "targetCurrency.isoCode",target = "currency")
    public abstract CurrencyRateJson mapToCurrencyJson(Rate currency);
    public abstract List<CurrencyRateJson> mapToCurrencyJson(List<Rate> currency);
    public abstract List<RateJson> mapToJson(List<Rate> rates);

    public Page<RateJson> mapToJson(Page<Rate> rates) {
        return new PageImpl<>(mapToJson(rates.getContent()), rates.getPageable(), rates.getTotalElements());
    }
}
