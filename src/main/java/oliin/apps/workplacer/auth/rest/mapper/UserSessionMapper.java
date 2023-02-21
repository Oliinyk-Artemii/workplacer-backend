package oliin.apps.workplacer.auth.rest.mapper;

import oliin.apps.workplacer.auth.rest.LoginController;
import oliin.apps.workplacer.auth.domain.model.UserCredentials;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper(componentModel = "spring")
public interface UserSessionMapper {
    UserCredentials toUserCredentials(LoginController.LoginRequest request);
}
