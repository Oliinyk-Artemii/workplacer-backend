package oliin.apps.workplacer.rest.mapper;

import oliin.apps.workplacer.domain.model.User;
import oliin.apps.workplacer.rest.LoginController;
import oliin.apps.workplacer.rest.model.AuthorityType;
import oliin.apps.workplacer.rest.model.UserResponse;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;
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
