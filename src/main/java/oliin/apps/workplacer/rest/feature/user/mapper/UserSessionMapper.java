package oliin.apps.workplacer.rest.feature.user.mapper;

import oliin.apps.workplacer.rest.feature.user.LoginController;
import oliin.apps.workplacer.domain.feature.user.model.UserCredentials;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper(componentModel = "spring")
public interface UserSessionMapper {
    UserCredentials toUserCredentials(LoginController.LoginRequest request);
}
