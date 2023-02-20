package oliin.apps.workplacer.sabre.rest.mapper;

import oliin.apps.workplacer.sabre.domain.model.UserInfo;
import oliin.apps.workplacer.sabre.rest.CreateUserController;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper(componentModel = "spring")
public interface UserInfoMapper {
    UserInfo toUserInfo(CreateUserController.CreateUserRequest request);

}
