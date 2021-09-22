package mz.co.checkmob.api.endpoint.domain;

import mz.co.checkmob.api.endpoint.presentation.EndpointJson;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class EndpointMapper {
    public static EndpointMapper INSTANCE = Mappers.getMapper(EndpointMapper.class);

    @Named("commandToModel")
    public abstract Endpoint mapToModel(CreateEndpointCommand command);

    @InheritInverseConfiguration
    public abstract EndpointJson mapToJson(Endpoint endpoint);
    public abstract List<EndpointJson> mapToJson(List<Endpoint> endpoints);

    public Page<EndpointJson> mapToJson(Page<Endpoint> endpoints) {
        return new PageImpl<>(mapToJson(endpoints.getContent()), endpoints.getPageable(), endpoints.getTotalElements());
    }

}
