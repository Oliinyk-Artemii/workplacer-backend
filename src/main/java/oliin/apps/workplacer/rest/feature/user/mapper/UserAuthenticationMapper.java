package oliin.apps.workplacer.rest.feature.user.mapper;

import oliin.apps.workplacer.domain.feature.user.model.UserRegistrationInfo;
import oliin.apps.workplacer.rest.feature.user.RegisterController;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper(componentModel = "spring")
public interface UserAuthenticationMapper {
    UserRegistrationInfo toUserInfo(RegisterController.RegisterRequest request);
}

