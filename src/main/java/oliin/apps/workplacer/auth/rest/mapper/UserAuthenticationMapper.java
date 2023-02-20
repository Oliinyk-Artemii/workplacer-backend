package oliin.apps.workplacer.auth.rest.mapper;

import oliin.apps.workplacer.auth.domain.model.UserRegistrationInfo;
import oliin.apps.workplacer.auth.rest.RegisterController;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper(componentModel = "spring")
public interface UserAuthenticationMapper {
    UserRegistrationInfo toUserInfo(RegisterController.RegisterRequest request);
}

