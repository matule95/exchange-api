package mz.co.checkmob.api.jobs.domain;

import mz.co.checkmob.api.jobs.presentation.RequestExecutorJson;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class RequestExecutorMapper {
    public static final RequestExecutorMapper INSTANCE = Mappers.getMapper(RequestExecutorMapper.class);

    @Named("commandToModel")
    public abstract RequestExecutor mapToModel(RequestExecutorCommand command);

    @InheritInverseConfiguration
    public abstract RequestExecutorJson mapToJson(RequestExecutor executor);

}
