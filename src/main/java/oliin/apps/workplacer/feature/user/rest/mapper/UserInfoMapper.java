package oliin.apps.workplacer.feature.user.rest.mapper;

import oliin.apps.workplacer.domain.model.user.UserInfo;
import oliin.apps.workplacer.feature.user.rest.CreateUserController;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper(componentModel = "spring")
public interface UserInfoMapper {
    UserInfo toUserInfo(CreateUserController.CreateUserRequest request);

}
