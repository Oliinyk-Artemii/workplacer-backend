package oliin.apps.workplacer.rest.feature.user.mapper;

import oliin.apps.workplacer.domain.feature.user.model.User;
import oliin.apps.workplacer.rest.feature.user.model.AuthorityType;
import oliin.apps.workplacer.rest.feature.user.model.UserResponse;
import org.mapstruct.*;

import java.util.Collections;
import java.util.Set;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper(componentModel = "spring", imports = {Collections.class, AuthorityType.class, Set.class})
public interface UserResponseMapper {
    @Mapping(source = "roles", target = "role", qualifiedByName = "rolesToRole")
    UserResponse toUserResponse(User user);

    @Named("rolesToRole")
    public static AuthorityType rolesToRole(Set<AuthorityType> roles) {
        for (AuthorityType type : AuthorityType.values()) {
            if (roles.contains(type)) {
                return type;
            }
        }
        return AuthorityType.EMPLOYEE;
    }

}
