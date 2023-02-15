package oliin.apps.workplacer.rest.mapper;

import oliin.apps.workplacer.domain.model.UserInfo;
import oliin.apps.workplacer.rest.RegisterController;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper(componentModel = "spring")
public interface UserAuthenticationMapper {
    UserInfo toUserCredentials(RegisterController.RegisterRequest request);
}

