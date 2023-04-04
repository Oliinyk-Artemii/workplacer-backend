package oliin.apps.workplacer.feature.user.rest.mapper;

import oliin.apps.workplacer.domain.model.user.UserRegistrationInfo;
import oliin.apps.workplacer.feature.user.rest.RegisterController;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper(componentModel = "spring")
public interface UserAuthenticationMapper {
    UserRegistrationInfo toUserInfo(RegisterController.RegisterRequest request);
}

