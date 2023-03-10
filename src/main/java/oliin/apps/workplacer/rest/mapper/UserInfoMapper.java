package oliin.apps.workplacer.rest.mapper;

import oliin.apps.workplacer.domain.model.UserInfo;
import oliin.apps.workplacer.rest.CreateUserController;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper(componentModel = "spring")
public interface UserInfoMapper {
    UserInfo toUserInfo(CreateUserController.CreateUserRequest request);

}
