package mz.co.checkmob.api.connections.domain;

import mz.co.checkmob.api.connections.presentation.ConnectionJson;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class ConnectionMapper {
    public static final ConnectionMapper INSTANCE = Mappers.getMapper(ConnectionMapper.class);

    @Named("commandToModel")
    @Mapping(target = "fromThirdParty", source = "fromThirdParty", ignore = true)
    @Mapping(target = "toThirdParty", source = "toThirdParty", ignore = true)
    public abstract Connection mapToModel(CreateConnectionCommand command);

    @InheritInverseConfiguration
    @Mapping(source = "requestExecutor",target = "frequency")
    public abstract ConnectionJson mapToJson(Connection connection);
    public abstract List<ConnectionJson> mapToJson(List<Connection> connections);

    public Page<ConnectionJson> mapToJson(Page<Connection> connections) {
        return new PageImpl<>(mapToJson(connections.getContent()), connections.getPageable(), connections.getTotalElements());
    }

}
