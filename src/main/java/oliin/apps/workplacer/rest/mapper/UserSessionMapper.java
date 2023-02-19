package oliin.apps.workplacer.rest.mapper;

import oliin.apps.workplacer.domain.model.UserCredentials;
import oliin.apps.workplacer.rest.LoginController;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper(componentModel = "spring")
public interface UserSessionMapper {
    UserCredentials toUserCredentials(LoginController.LoginRequest request);
}
