package oliin.apps.workplacer.feature.user.rest.mapper;

import oliin.apps.workplacer.feature.user.rest.LoginController;
import oliin.apps.workplacer.domain.model.user.UserCredentials;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper(componentModel = "spring")
public interface UserSessionMapper {
    UserCredentials toUserCredentials(LoginController.LoginRequest request);
}
