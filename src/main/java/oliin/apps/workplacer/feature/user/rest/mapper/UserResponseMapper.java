package oliin.apps.workplacer.feature.user.rest.mapper;

import oliin.apps.workplacer.domain.model.Company;
import oliin.apps.workplacer.domain.model.Office;
import oliin.apps.workplacer.domain.model.user.User;
import oliin.apps.workplacer.feature.user.domain.model.AuthorityType;
import oliin.apps.workplacer.feature.user.domain.model.UserResponse;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@MapperConfig(unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper(componentModel = "spring", imports = {Collections.class, AuthorityType.class, Set.class})
public interface UserResponseMapper {
    @Mapping(source = "roles", target = "role", qualifiedByName = "rolesToRole")
    @Mapping(source = "companies", target = "companyIds", qualifiedByName = "companiesToCompanyIds")
    @Mapping(source = "offices", target = "officeIds", qualifiedByName = "officesToOfficeIds")
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

    @Named("companiesToCompanyIds")
    public static List<String> companiesToCompanyIds(Set<Company> companies) {
        return companies.stream().map(Company::getId).toList();
    }

    @Named("officesToOfficeIds")
    public static List<String> officesToOfficeIds(Set<Office> offices) {
        return offices.stream().map(Office::getId).toList();
    }

}
