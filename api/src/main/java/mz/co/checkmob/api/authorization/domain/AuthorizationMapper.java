package mz.co.checkmob.api.authorization.domain;

import mz.co.checkmob.api.authorization.presentation.AuthorizationJson;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class AuthorizationMapper {
    public static AuthorizationMapper INSTANCE = Mappers.getMapper(AuthorizationMapper.class);

    @Named("commandToModel")
    public abstract Authorization mapToModel(AuthorizationCommand command);

    @InheritInverseConfiguration
    public abstract AuthorizationJson mapToJson(Authorization authorization);
    public abstract List<AuthorizationJson> mapToJson(List<Authorization> authorizations);

    public Page<AuthorizationJson> mapToJson(Page<Authorization> authorizations) {
        return new PageImpl<>(mapToJson(authorizations.getContent()), authorizations.getPageable(), authorizations.getTotalElements());
    }

}